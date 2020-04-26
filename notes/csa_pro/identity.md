# Identity and Federation

- Users and account all in AWS
- AWS Organizations
- Federation with SAML
- Federation without SAML with a custom IdP
- Federation with SSO for multiple accounts with AWS Organzations
- Web Identity Federation
- Cognito
- AD on AWS
-- Microsoft AD
-- AD Connector
-- Simple AD
- AWS SSO to connect to multiple AWS Account (via Organizations) and SAML apps

## IAM Roles vs Resource Based Policy

Resource based policy allows an user to access resource in a different account with existing credentials.

Assuming a role means that you current credentials are not valid any more. Also, assuming a role will make assumed role account owner of the S3 object created.

**Question** Scanning a DynamoDB table on account A and writing the content to S3 bucket on account B.

**Answer** Resource based policy allows to use single session. Assuming a role means that two sessions needs to be used, one to read with assumed role and one to write with existing credentials.

## STS

External ID and confused deputy.

```
{
  "Version": "2012-10-17",
  "Statement": {
    "Effect": "Allow",
    "Principal": {"AWS": "1234567890"},
    "Action": "sts:AssumeRole",
    "Condition": {"StringEquals": {"sts:ExternalId": "12345"}}
  }
}
```

**Question** How to secure IAM role that is shared to 3rd party so that only that 3rd party can assume the role? How does this protect you?

**Answer** Use a shared secret, external ID, and enforce the in the role policy statement. This enforces the 3rd party to submit shared secret when assuming a role. If a malicious party tries to submit your role ARN, they also need to know the shared secret.

## Identity federation

### SAML 2.0

Supported by AD / ADFS and uses `sts:AssumeRoleWithSAML` with the SAML assertion.

### Custom identity provider

Uses `sts:AssumeRole` or `sts:GetFederationToken`. Custom IdP "talks" to AWS STS instead of user and create temporary credentials.

### Web Identity Federation - AssumeRoleWithWebIdentity

Not recommended by AWS, use Cognito. Client calls AWS STS `sts:AssumeRoleWithWebIdentity` only.

### Web Identity Federation - AWS Cognito

Client gets Cognito tokens from AWS Cognito and can use Cognito tokens to get credentials from AWS STS. Additional support over AssumeRoleWithWebIdentity: anonymous uses, support for MFA and data synchronization.

Token Vending Machine (TVM) is replaced by Cognito.

### AWS Directory Service

Two-way forest trust means that users of either AD can access either AD. Forest trust is different that synchronization. Users exist independently on different AD.

#### AWS Managed Microsoft AD

Standalone or joined repository. Trust between on premis AD. Users defined in two places. Deployed to your VPC. Seamlessly Domain Join EC2 instance. Integrations: RDS, AWS Workspaces, AWS SSO. Multi-AZ depoloyment. Supports two-way Forest trust.

Connection to on premis needs Direct Connection or VPN.

Replication needs a self managed Microsoft AD on VPC and two-way forest trust between replica and AWS Managed Microsoft AD.

#### AD Connector

Proxy to on premis AD. Users defined only on premis. No caching. Requires also Direct Connection or VPN.

#### Simple AD

AD-compatible managed directory service which can't be join with on premis AD.

### AWS Organizations

Master account and child accounts. Child account must have a role defined in order to master account have access, just being part of organization is not enough. These role should be create with AWS Cloudformation Stack sets. Dedicated account for logging or security. AWS SSO allows single login to all account.

Consolidated billing features vs all features. From All features back to consolidated billing is not possible.

All features support SCP and invited account must accept all features.

SCP to whitelist or blacklist IAM actions. Applied at the root, OU or account level. SCP applies to all users and groups of the account, even root. Can't restrict service linked roles. SCP must have explicit allow, default all is disabled.

Reserved instances and savings plans sharing can be enabled to save costs.

With AWS RAM (Resource Access Manager) you can share VPC Subnets, AWS Transit Gateway, Route53 Resolver Rules and License Manager configurations. Share VPC with multiple accounts, use RAM.

### AWS SSO

Multiple AWS account and 3rd party applications. Integrated to AWS Organizations and CloudTrail. Can integrate to on premise AD.

AWS SSO vs AssumeRoleWithSAML - AWS SSO does not require 3rd pary IdP Login portal.
