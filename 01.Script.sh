#limpiar pantalla
clear

#crear estructura de directorios
mkdir -p ejercicio/{a/f/{j/{p,q/w/y},k/r/x/z},b/g,c/h/{i,m/{s,t}},d,e/t/{n,o/v}}

#cambiar al directorio y
cd ejercicio/a/f/j/q/w/y

#crear 5 archivos
touch archivo1.cpp archivo2.java file3.cpp file4.js archivo5.java

#copiar los archivos a n
cp *.* ../../../../../../e/t/n

#copiar archivos con alguna extension a h
cp *.cpp ../../../../../../c/h

#copiar archivos con una letra en la cuarta posicion y extension especificas a t
cp ???h*.cpp ../../../../../../e/t

#copiar n a z
cd ../../../../../../e/t
cp -r n ../../a/f/k/r/x/z

#cambiar permisos de los archivos de y
cd ../../a/f/j/q/w/y
chmod 000 archivo1.cpp  #todos los permisos negados
chmod 777 archivo2.java #todos los permisos permitidos
chmod 111 file3.cpp     #solo permiso de ejecucion
chmod 222 file4.js      #solo permiso de escritura

