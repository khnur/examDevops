FROM node:14 as build
LABEL authors="nkozhamuratov"

WORKDIR /app

COPY package.json ./
COPY package-lock.json ./
RUN npm install

ARG REACT_APP_SERVER_URL
ENV REACT_APP_SERVER_URL=http://localhost:8000

COPY . ./
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
