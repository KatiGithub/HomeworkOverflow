# FROM node:14-alpine AS builder
# WORKDIR /usr/src/app
# COPY yarn.lock ./
# RUN yarn install
# COPY . .
# RUN yarn start

# FROM nginx:alpine
# COPY --from=frontend-build /app/dist/frontend-angular /usr/share/nginx/html
# EXPOSE 80

FROM node:14-alpine AS builder
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.13.12-alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /usr/src/app/dist /usr/share/nginx/html
EXPOSE 4200
CMD ["nginx", "-g" ,"daemon off;"]