# Etapa de build do Angular
FROM node:18 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar package.json e package-lock.json
COPY package*.json ./

# Instalar as dependências do projeto
RUN npm install

# Copiar o restante dos arquivos do projeto
COPY . .

RUN npm install
RUN npm run build --prod

# Etapa de runtime do Nginx
FROM nginx:alpine

# Remover o conteúdo padrão do diretório HTML do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copiar o conteúdo do build do Angular para o diretório HTML do Nginx
COPY nginx.conf  /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/front-car-user-system/browser /usr/share/nginx/html


# Expor a porta 80 para o Nginx
EXPOSE 80

