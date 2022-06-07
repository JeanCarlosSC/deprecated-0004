# su función es gestionar muchos servidores y clientes de forma concurrente

# installing
sudo apt update
sudo apt install nginx
systemctl status nginx
nginx -t

# another important data
cd /etc/nginx/
cd states-enabled/
nano default

# por cada cambio se reinicia el servidor
sudo systemctl restart nginx

# nginx config file
cd /etc/nginx/
nano nginx.config
