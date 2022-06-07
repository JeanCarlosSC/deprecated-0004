# Primer numero de la serie
a=0

# Segundo numero de la serie
b=1

echo "Los primeros 10 valores de la serie Fibonacci son : "

for (( i=0; i<10; i++ ))
do
	echo -n "$a "
	fn=$((a + b))
	a=$b
	b=$fn
done
