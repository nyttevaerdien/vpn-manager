To apply configuration on server run:

`cd configuration && scp -r . root@217.160.164.219:app/`

Quick restart:

`docker compose pull && docker compose up -d`


todo: 
add cronjob for certbot renewal
`docker compose run --rm certbot renew`