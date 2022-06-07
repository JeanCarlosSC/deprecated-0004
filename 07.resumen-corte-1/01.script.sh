#!/bin/bash
# ejecutar con la forma ./ para su correcta ejecución

# limpiar pantalla
clear

# estructura de directorios
mkdir -p nombre/{a/f/{j/{p,q/w/y},k/r/x/z},b/g,c/h/{l,m/{s,t}},d,e/t/{n,o/v}}

# cambiar al directorio y
cd nombre/a/f/j/q/w/y

# crear 5 archivos con diferente extensión
touch file1.java file2.py file3.tsx file4.json file5.tsx

# copiar todos los archivos al directorio N
cp * ../../../../../../e/t/n

# copiar todos los archivos con una extensión al directorio H
cp *.tsx ../../../../../../c/h

# copiar todos los archivos con una letra específica en la cuarta posición del nombre y una extensión específica (a w)
cp ???e*.json ../

# copiar el directorio n al directorio z
cd ../../../../../../
cp e/t/n/* a/f/k/r/x/z

# cambiar los permisos del directorio y de esta forma
chmod 000 a/f/j/q/w/y/file1.java # un archivo con todos los permisos negados
chmod 721 a/f/j/q/w/y/file2.py # otro con p. de usuario permitidos, otros con p. de ejecución y grupo con p. de escritura
