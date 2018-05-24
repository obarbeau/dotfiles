# Dotfiles for [@obarbeau](http://twitter.com/obarbeau)

inspired by https://bitbucket.org/durdn/cfg

## Requirements

- Git

```shell
DOTFILES_DIR=$HOME/dotfiles

alias config='/usr/bin/git --git-dir=$DOTFILES_DIR/ --work-tree=$HOME'
```

## Install

Install config tracking in your $HOME by running:

```shell
git init --bare $DOTFILES_DIR
config config --local status.showUntrackedFiles no
# avoid weird recursions
echo "dotfiles" >> .gitignore

config add .clojure/deps.edn
config add .lein/profiles.edn
# add other files...

config commit --no-gpg-sign -m "added files"
config remote add origin git@github.com:$USER/dotfiles.git
config push --set-upstream origin master
config status
```

## Restore on another computer

1)
```shell
git clone --bare git@github.com:$USER/dotfiles.git $DOTFILES_DIR
config config --local status.showUntrackedFiles no
```

2)
```shell
config checkout
if [ $? = 0 ]; then
  echo "Checked out config.";
  else
    echo "Backing up pre-existing and new dot files.";
    mkdir -p $DOTFILES_DIR-backup
    # also create eventual directory structure before copying files
    # escape shell commands in the 'echo' so that they are evaluated by final 'bash'
    config checkout 2>&1 | awk {'print $2'} | xargs -I{} echo "mkdir -p $DOTFILES_DIR-backup/\$(dirname {}); cp {} $DOTFILES_DIR-backup/{}" | bash
fi;
config checkout
# Resolves possible conflicts
```

## Synchronize changes

Begin at step 2 above.
