"""
Utility to make multipart uploads to S3
"""

import logging
import math
from multiprocessing import Pool
import os
import boto
from filechunkio import FileChunkIO

MB_5 = 5242880

def _upload_part(multipart, part_num, source_path, 
                 offset, abytes, amount_of_retries=10):
    """
    Uploads a part with retries.
    """
    def _upload(retries_left=amount_of_retries):
        """
        Upload part to S3
        """
        try:
            logging.info('Start uploading part #%d ...', part_num)
            with FileChunkIO(source_path, 'r', offset=offset,
                             bytes=abytes) as filepointer:
                multipart.upload_part_from_file(fp=filepointer,
                                                part_num=part_num)
        # pylint: disable=W0703
        except Exception, exc:
        # pylint: enable=W0703
            if retries_left:
                _upload(retries_left=retries_left - 1)
            else:
                logging.error('... Failed uploading part #%d', part_num)
                raise exc
        else:
            logging.info('... Uploaded part #%d', part_num)

    _upload()


def upload(bucketname, keyname, source_path, parallel_processes=4):
    """
    Parallel multipart upload.
    """
    bucket = boto.connect_s3().get_bucket(bucketname)

    multipart = bucket.initiate_multipart_upload(keyname)

    source_size = os.stat(source_path).st_size
    bytes_per_chunk = max(int(math.sqrt(MB_5) * math.sqrt(source_size)), MB_5)
    chunk_amount = int(math.ceil(source_size / float(bytes_per_chunk)))

    pool = Pool(processes=parallel_processes)
    for i in range(chunk_amount):
        offset = i * bytes_per_chunk
        remaining_bytes = source_size - offset
        abytes = min([bytes_per_chunk, remaining_bytes])
        pool.apply_async(_upload_part, 
                         [multipart, i + 1, source_path, offset, abytes])
    pool.close()
    pool.join()
    
    if len(multipart.get_all_parts()) == chunk_amount:
        multipart.complete_upload()
        logging.info("Multipart upload successfull!")
    else:
        multipart.cancel_upload()
        logging.error("Failed to upload all parts!")
        raise Exception("Failed to upload all parts!")


