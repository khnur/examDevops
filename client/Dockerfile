FROM node:14 as build
LABEL authors="nkozhamuratov"

WORKDIR /app

COPY package.json ./
COPY package-lock.json ./
RUN npm install

ARG REACT_APP_SERVER_URL
ENV REACT_APP_SERVER_URL=http://localhost:8000

COPY . ./

CMD ["npm", "start"]
