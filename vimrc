" be iMproved
set nocompatible

" map jj to enter normal mode
imap jj <Esc>

" allow space to be used the same as :
nnoremap <Space> :

" backspace in insert mode works like normal editor
set backspace=2

" auto indenting
set autoindent

" get rid of annoying ~file
set nobackup

set expandtab
set smarttab
set shiftwidth=4
set tabstop=4
set ruler
syntax enable

set incsearch
set ignorecase
set linebreak
set scrolloff=3
set mouse=nicr

set background=dark
" other decent choices: desert elflord
color torte

" add warning line at 80 characters of column
if exists('+colorcolumn')
    set colorcolumn=80
else
    au BufWinEnter * let w:m2=matchadd('ErrorMsg', '\%>80v.\+', -1)
endif

" Show trailing whitespace
au BufWinEnter * let w:m2=matchadd('ErrorMsg', '\s\+$', -1)

" fix common mistakes
com! W w
com! Q q
com! Wq wq
com! WQ wq
