#!/bin/bash

# Create a large file with *nix shell.

THIS_SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
FILE_PATH_TO_USE_AS_BASE="$THIS_SCRIPT_DIR$(echo /UpperCaseConverter.java)"
TEMP_FILE_PATH="$THIS_SCRIPT_DIR$(echo /tmp.txt)"
FILE_PATH_TO_GENERATE="$THIS_SCRIPT_DIR$(echo /../../../../../../storage/input.txt)"
# This limit is currently not strict, since generation loop increases output file size by doubling it each time.
FILE_MAX_BYTES=1000000000

rm -f $FILE_PATH_TO_GENERATE $TEMP_FILE_PATH

cat $FILE_PATH_TO_USE_AS_BASE >> $FILE_PATH_TO_GENERATE

# In order to speed up generation, file content is doubles at each loop.
# $TEMP_FILE_PATH is required because append operator (">>") cannot work with same file both as input and as output.
while [ $(stat --printf="%s" $FILE_PATH_TO_GENERATE) -lt $FILE_MAX_BYTES ]
do
  cat $FILE_PATH_TO_GENERATE >> $TEMP_FILE_PATH
  cat $TEMP_FILE_PATH >> $FILE_PATH_TO_GENERATE
done

rm -f $TEMP_FILE_PATH

# Sources:
#  - https://stackoverflow.com/questions/59895/how-to-get-the-source-directory-of-a-bash-script-from-within-the-script-itself
#  - https://unix.stackexchange.com/questions/16640/how-can-i-get-the-size-of-a-file-in-a-bash-script
#  - https://tldp.org/LDP/Bash-Beginners-Guide/html/sect_09_02.html
#  - https://stackoverflow.com/questions/20615217/bash-bad-substitution --> this explains why #!/bin/bash is required instead of #!/bin/sh
