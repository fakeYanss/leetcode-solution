#!/bin/bash

########################################################
# Auto build leetcode algorithm table markdown content.
#
# leetcode solution file name pattern:
# {type}/_{index}_{title}_{difficulty}/Solution.java
# ps: title can concat with underline.
########################################################

_base_path=$(pwd)
_leetcode_algo_table_md="$_base_path/leetcode-algorithm-table.md"
_code_path='src/main/java/me/foreti/leetcode'
folders=$(find $_code_path -name '*_*' -a -type d)

declare -a algo_arr       # store algo path
declare -a index_arr      # store algo index
declare -a type_arr       # store algo type
declare -a title_arr      # store algo title
declare -a difficulty_arr # store algo difficulty

for floder in $folders; do
    IFS='/' read -ra ADDR <<<"$floder"
    algo_pkg=${ADDR[${#ADDR[@]} - 1]}

    type=${ADDR[${#ADDR[@]} - 2]}

    IFS='_' read -ra ADDR <<<"$algo_pkg"
    index=${ADDR[1]}
    index_arr[$index]=1

    algo_arr[$index]=$algo_pkg
    type_arr[$index]=$type

    difficulty=${ADDR[${#ADDR[@]} - 1]}
    difficulty_arr[$index]=$difficulty

    last_last=$((${#ADDR[@]} - 3))
    title_info=${ADDR[@]:2:$last_last}
    title=${title_info// / }
    title_arr[$index]=$title
done

# echo ${!index_arr[@]}
# echo ${algo_arr[@]}
# echo ${type_arr[@]}
# echo ${title_arr[@]}
# echo ${difficulty_arr[@]}

# build markdown content
echo "## LeetCode" >$_leetcode_algo_table_md
echo "### LeetCode Algorithm" >>$_leetcode_algo_table_md
echo "| # | Title | Solution  | Difficulty |" >>$_leetcode_algo_table_md
echo "| --- | --- | --- | --- |" >>$_leetcode_algo_table_md
for index in ${!index_arr[@]}; do
    title=${title_arr[$index]}
    path=${title// /-}

    # optimize title
    title=${title//ii/II}
    title=${title//iii/III}
    title=${title//iv/IV}
    # title=$(awk '{for (i=1;i<=NF;i++)printf toupper(substr($i,0,1))substr($i,2,length($i))" ";printf "\n"}' <<< $title)
    title=$(awk '{for (i=1;i<=NF;i++)if(substr($i,0,1) ~ /^[0-9]+$/) {printf substr($i,0,1)toupper(substr($i,2,1))substr($i,3,length($i))" "} else {printf toupper(substr($i,0,1))substr($i,2,length($i))" "};printf "\n"}' <<<$title)
    # title=$(echo -n $title|python3 -c "import sys; print(sys.stdin.read().title())")
    # echo $title

    difficulty=${difficulty_arr[$index]}
    difficulty="$(tr '[:lower:]' '[:upper:]' <<<${difficulty:0:1})${difficulty:1}"
    echo "| $index | [$title](https://leetcode.com/problems/$path) | [Java](./src/main/java/me/foreti/leetcode/${type_arr[$index]}/${algo_arr[$index]}/Solution.java) | $difficulty |" >>$_leetcode_algo_table_md
done
