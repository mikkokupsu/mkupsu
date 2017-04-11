" Python style
setlocal tabstop=4
setlocal shiftwidth=4
setlocal expandtab
setlocal autoindent
setlocal smarttab
setlocal formatoptions=croql

" Python mode configuration
hi pythonSelf ctermfg=68 guifg=#5f87d7 cterm=bold gui=bold

" isort configuration
nnoremap <LocalLeader>i :!isort %<CR><CR>

" yapf configuration
nnoremap <LocalLeader>= :0,$!yapf<CR>

" Enable jedi
let g:jedi#auto_initialization = 1

" Custom pymode configuration
let g:pymode_folding = 0
let g:pymode_options_max_line_length = 79
