#!/bin/sh

git config --global user.email "charlottewelle@yahoo.fr"
git config --global user.name "Welle Charlotte" 
cd /home/travis/repository
git init
git add -f .
git remote rm origin
git remote add origin https://welle:$GITHUB_API_KEY@github.com/welle/maven-repostitory.git
git add -f .
git commit -m "Travis build $TRAVIS_BUILD_NUMBER pushed [skip ci] "
git push -fq origin master > /dev/null
echo -e "Done\n"