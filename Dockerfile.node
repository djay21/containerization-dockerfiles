FROM node:latest
WORKDIR /models
COPY package.json .
RUN npm i
COPY . .
EXPOSE 3000
RUN chmod 777 -R /models
CMD ["bash", "start.sh"]
