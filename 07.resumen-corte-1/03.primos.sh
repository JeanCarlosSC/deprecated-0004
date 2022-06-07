#!/bin/bash
clear
echo Los números primos del 1 al 100 son:

primo() {
    numero_actual=$1
    estado=0
    i=2

    if test $numero_actual -eq 1
        then estado=1
    fi

    while test $i -le `expr $numero_actual / 2`
    do
        if test `expr $numero_actual % $i` -eq 0
        then
            estado=1
        fi
        i=`expr $i + 1`
    done

    if test $estado -eq 0
        then echo $numero_actual
    fi
}

desde=1
hasta=100

for (( number=$desde; number<=$hasta; number++ ))
do
    primo $number
done
