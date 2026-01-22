sudo docker pull mirkogutierrezappx/param:latest

sudo docker stop param-container 2>/dev/null
sudo docker rm param-container 2>/dev/null

sudo docker build -t param .

sudo docker run \
           --restart always \
           -d -p 8087:8087 \
           --env-file .env \
           --network appx \
           --add-host=host.docker.internal:host-gateway \
           --name param-container param \
    mirkogutierrezappx/param:latest


