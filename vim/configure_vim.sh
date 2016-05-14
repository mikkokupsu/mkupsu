#!/bin/bash

set -eu

VIM=vim
VIM_INSTALLED="which ${VIM}"
VIM_PACKAGE=vim-nox

GIT=git
GIT_INSTALLED="which ${GIT}"
GIT_PACKAGE="${GIT}"

FLAKE8=flake8
FLAKE8_INSTALLED="which ${FLAKE8}"
FLAKE8_PACKAGE="${FLAKE8}"

VIM_RC=~/.vimrc
VIM_HOME=~/.vim
VIM_BUNDLE_DIR="${VIM_HOME}"/bundle
VIM_SYNTAX_DIR="${VIM_HOME}"/syntax
VIM_AUTOLOAD_DIR="${VIM_HOME}"/autoload

PATHOGEN=pathogen.vim
PATHOGEN_SRC=https://tpo.pe/pathogen.vim

ULTISNIPS=ultisnips
ULTISNIPS_SRC=https://github.com/SirVer/ultisnips.git

INDENTLINE=indentLine
INDENTLINE_SRC=https://github.com/Yggdroot/indentLine.git

DELIMITMATE=delimitMate
DELIMITMATE_SRC=https://github.com/Raimondi/delimitMate.git

PIGVIM=pig.vim
PIGVIM_SRC=https://github.com/vim-scripts/pig.vim.git

SYNTASTIC=syntastic
SYNTASTIC_SRC=https://github.com/scrooloose/syntastic

FUGITIVE=vim-fugitive
FUGITIVE_SRC=git://github.com/tpope/vim-fugitive.git

NERDTREE=nerdtree
NERDTREE_SRC=https://github.com/scrooloose/nerdtree.git

function install_package {
    PACKAGE=$1

    echo "Installing ${PACKAGE}"
    sudo dnf install -q -y "${PACKAGE}"
}

function enable_syntax_highlighting {
    echo "syntax on" >> "${VIM_RC}"
}

function enable_trailing_whitespace_highlighting {
    echo "
:highlight ExtraWhitespace ctermbg=red guibg=red
:match ExtraWhitespace /\s\+$/" >> "${VIM_RC}"
}

function install_if_needed {
    CMD_NAME=$1
    CHECK_CMD=$2
    INSTALL_CMD=$3

    echo "Installing ${CMD_NAME}, if needed"
    if [ "$(${CHECK_CMD})" == "" ]; then
        install_package "${INSTALL_CMD}"
    fi
}

install_if_needed "${VIM}" "${VIM_INSTALLED}" "${VIM_PACKAGE}"
install_if_needed "${GIT}" "${GIT_INSTALLED}" "${GIT_PACKAGE}"
install_if_needed "${FLAKE8}" "${FLAKE8_INSTALLED}" "${FLAKE8_PACKAGE}"

# Verify that vimrc exists
touch "${VIM_RC}"

# Enabling syntax higlighting
if [ "$(grep -c '^syntax' < ${VIM_RC})" == "0" ]; then
    enable_syntax_highlighting
fi

if [ "$(grep -c '^:match ExtraWhitespace ' < ${VIM_RC})" == "0" ]; then
    enable_trailing_whitespace_highlighting
fi

# Verify that Vim bundle folder exists
mkdir -p "${VIM_BUNDLE_DIR}"
# Verify that Vim syntax folder exists
mkdir -p "${VIM_SYNTAX_DIR}"
# Verify that Vim autoload folder exists
mkdir -p "${VIM_AUTOLOAD_DIR}"

if [ ! -f "${VIM_AUTOLOAD_DIR}/${PATHOGEN}" ]; then
    curl -LSso "${VIM_AUTOLOAD_DIR}/${PATHOGEN}" "${PATHOGEN_SRC}"
    echo "execute pathogen#infect()" >> ${VIM_RC}
fi

function do_nothing {
    :
}

function install_plugin {
    PLUGIN_NAME="$1"
    PLUGIN_SRC="$2"
    PLUGIN_CONF_FUNC="${3:-do_nothing}"
    PLUGIN_SETUP_FUNC="${4:-do_nothing}"


    if [ ! -d "${VIM_BUNDLE_DIR}/${PLUGIN_NAME}" ]; then
        pushd "${VIM_BUNDLE_DIR}"
        git clone "${PLUGIN_SRC}" "${PLUGIN_NAME}"
        popd
        ${PLUGIN_CONF_FUNC}
    else
        pushd "${VIM_BUNDLE_DIR}/${PLUGIN_NAME}"
        git pull
        popd
    fi

    ${PLUGIN_SETUP_FUNC}
}

install_plugin "${ULTISNIPS}" "${ULTISNIPS_SRC}"

function configure_indentline {
    echo "
let g:indentLine_color_term = 239
let g:indentLine_color_gui = '#09AA08'
let g:indentLine_char = '│'
set autoindent
set expandtab
set tabstop=4" >> "${VIM_RC}"
}

install_plugin "${INDENTLINE}" "${INDENTLINE_SRC}" "configure_indentline"
install_plugin "${DELIMITMATE}" "${DELIMITMATE_SRC}"

function configure_pigvim {
    echo "
augroup filetypedetect
  au BufNewFile,BufRead *.pig set filetype=pig syntax=pig
augroup END" >> "${VIM_RC}"
}

function refresh_pigvim {
    cp "${VIM_BUNDLE_DIR}/${PIGVIM}"/syntax/* "${VIM_SYNTAX_DIR}"
}

install_plugin "${PIGVIM}" "${PIGVIM_SRC}" "configure_pigvim" "refresh_pigvim"

function configure_syntastic {
    echo "
set statusline+=%#warningmsg#
set statusline+=%{SyntasticStatuslineFlag()}
set statusline+=%*

let g:syntastic_always_populate_loc_list = 1
let g:syntastic_auto_loc_list = 1
let g:syntastic_check_on_open = 1
let g:syntastic_check_on_wq = 0
let g:syntastic_python_checkers = ['flake8']
" >> "${VIM_RC}"
}

install_plugin "${SYNTASTIC}" "${SYNTASTIC_SRC}" "configure_syntastic"

function refresh_fugitive {
    vim -u NONE -c "helptags ${VIM_BUNDLE_DIR}/${FUGITIVE}/doc" -c q
}

install_plugin "${FUGITIVE}" "${FUGITIVE_SRC}" "refresh_fugitive"

function configure_nerdtree {
    echo ":command ND NERDTree" >> "${VIM_RC}"
}

function refresh_nerdtree {
    vim -u NONE -c "Helptags" -c q
}

install_plugin "${NERDTREE}" "${NERDTREE_SRC}" "configure_nerdtree" "refresh_nerdtree"
