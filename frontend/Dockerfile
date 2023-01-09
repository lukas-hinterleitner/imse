FROM node:18.12.1-alpine as build
WORKDIR /app
COPY package*.json /app/
RUN npm install -g ionic
RUN npm install
COPY ./ /app/
RUN ionic build --prod
FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/www/ /usr/share/nginx/html/
COPY --from=build /app/nginx/certificate.crt /etc/nginx/certificate.crt
COPY --from=build /app/nginx/private.key /etc/nginx/private.key
EXPOSE 80

CMD ["/bin/sh", "-c", "envsubst < /usr/share/nginx/html/assets/env.template.js > /usr/share/nginx/html/assets/env.js  && exec nginx -g 'daemon off;'"]
