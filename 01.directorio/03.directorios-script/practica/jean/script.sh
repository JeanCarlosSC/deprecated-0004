#!/bin/bash
mkdir -p a b/c/d
cd a
touch archivo1 archivo2 file3 file4
cp file* ../b
cp ??c* ../b/c/d
cd ../b/c/d
chmod 000 archivo1 archivo2
chmod u=r,g=w,o=x archivo1 archivo2
