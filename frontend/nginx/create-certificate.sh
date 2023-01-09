#!/bin/sh
openssl genrsa -aes128 -out private_with_password.key --passout pass:"imse" 2048
openssl rsa -in private_with_password.key -out private.key --passin pass:"imse"
openssl req -new -days 365 -key private.key -out request.csr
openssl x509 -in request.csr -out certificate.crt -req -signkey private.key -days 365
