#!/usr/bin/env bash

set -eu

VIM=vim
VIM_INSTALLED="which ${VIM}"
VIM_PACKAGE=vim-nox

GIT=git
GIT_INSTALLED="which ${GIT}"
GIT_PACKAGE="${GIT}"

FLAKE8=flake8
FLAKE8_INSTALLED="which ${FLAKE8}"
FLAKE8_PACKAGE="python-${FLAKE8}"

VIM_RC=~/.vimrc
VIM_HOME=~/.vim
VIM_BUNDLE_DIR="${VIM_HOME}"/bundle
VIM_SYNTAX_DIR="${VIM_HOME}"/syntax
VIM_AUTOLOAD_DIR="${VIM_HOME}"/autoload
VIM_FTPLUGIN_DIR="${VIM_HOME}"/ftplugin

PATHOGEN=pathogen.vim
PATHOGEN_SRC=https://tpo.pe/pathogen.vim

INDENTLINE=indentLine
INDENTLINE_SRC=https://github.com/Yggdroot/indentLine.git

DELIMITMATE=delimitMate
DELIMITMATE_SRC=https://github.com/Raimondi/delimitMate.git

PYTHON_SYNTAX=python-syntax
PYTHON_SYNTAX_SRC=https://github.com/vim-python/python-syntax

PIGVIM=pig.vim
PIGVIM_SRC=https://github.com/vim-scripts/pig.vim.git

JSHINT=jshint
JSHINT_SRC=https://github.com/wookiehangover/jshint.vim

SCALA=vim-scala
SCALA_SRC=https://github.com/derekwyatt/vim-scala

DOCKERFILE=dockerfile
DOCKERFILE_SRC=https://github.com/ekalinin/dockerfile.vim

MAKEFILE=makefile
MAKEFILE_SRC=https://github.com/c9s/vim-makefile

AVROVIM=avrovim
AVROVIM_SRC=https://github.com/dln/avro-vim.git

function install_package {
    PACKAGE=$1

    echo "Installing ${PACKAGE}"
    sudo dnf install -q -y "${PACKAGE}"
}

function pip_install_package {
    PACKAGE=$1
    echo "Installing ${PACKAGE}"
    sudo pip install --quiet --upgrade "${PACKAGE}"
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

rm -fr "${VIM_HOME}"

# Verify that vimrc exists
cp vimfiles/vimrc "${VIM_RC}"

mkdir -p "${VIM_BUNDLE_DIR}"
mkdir -p "${VIM_SYNTAX_DIR}"
mkdir -p "${VIM_AUTOLOAD_DIR}"
mkdir -p "${VIM_FTPLUGIN_DIR}"

cp vimfiles/ftplugin/* "${VIM_FTPLUGIN_DIR}"

function install_plugin {
    PLUGIN_NAME="$1"
    PLUGIN_SRC="$2"

    if [ ! -d "${VIM_BUNDLE_DIR}/${PLUGIN_NAME}" ]; then
        pushd "${VIM_BUNDLE_DIR}"
        git clone "${PLUGIN_SRC}" "${PLUGIN_NAME}"
        popd
    else
        pushd "${VIM_BUNDLE_DIR}/${PLUGIN_NAME}"
        git pull
        popd
    fi
}

curl -LSso "${VIM_AUTOLOAD_DIR}/${PATHOGEN}" "${PATHOGEN_SRC}"

install_plugin "${INDENTLINE}" "${INDENTLINE_SRC}"
install_plugin "${DELIMITMATE}" "${DELIMITMATE_SRC}"

install_plugin "${PYTHON_SYNTAX}" "${PYTHON_SYNTAX_SRC}"
cp "${VIM_BUNDLE_DIR}/${PYTHON_SYNTAX}"/syntax/* "${VIM_SYNTAX_DIR}"

install_plugin "${PIGVIM}" "${PIGVIM_SRC}"
cp "${VIM_BUNDLE_DIR}/${PIGVIM}"/syntax/* "${VIM_SYNTAX_DIR}"

install_plugin "${JSHINT}" "${JSHINT_SRC}"

install_plugin "${SCALA}" "${SCALA_SRC}"

install_plugin "${DOCKERFILE}" "${DOCKERFILE_SRC}"

install_plugin "${MAKEFILE}" "${MAKEFILE_SRC}"

install_plugin "${AVROVIM}" "${AVROVIM_SRC}"
cp "${VIM_BUNDLE_DIR}/${AVROVIM}"/syntax/* "${VIM_SYNTAX_DIR}"
