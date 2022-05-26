# estructura de directorios
mkdir -p inicio/{a/b/c,d/e}

# crear 4 archivos en c
cd inicio/a/b/c
touch ar1 fi2 er3 di4

# copiar c a d
cp ar1 ../../../d/
cp di4 ../../../d/
cp er3 ../../../d/
cp fi2 ../../../d/

# negar todos los permisos en d
cd ../../../d
chmod u=---,g=---,o=--- ar1 di4 er3 fi2

# copiar algún archivo de c a e usando el símbolo ?
cd ../a/b/c/
cp ?i? ../../../d/e

# negar todos los permisos en e
cd ../../../d/e
chmod 000 di4 fi2

# habilitar (r-u, w-g, x-o)
chmod 421 di4
