#!/bin/bash
# command line arg for branch
BRANCH=$1
echo $BRANCH
# start ssh agent and add key to it
eval "$(ssh-agent)"
ssh-add ~/.ssh/github
# pull down changes
git pull
# push up changes
git push -u origin $BRANCH