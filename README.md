<div align="center" id="top"> 
  <img src="docs/img/catalog.png" alt="Catalog" />

&#xa0;

  <!-- <a href="https://catalog.netlify.app">Demo</a> -->

<h1 align="center">Catalog</h1>

<!-- Status -->

 <h4 align="center">
	🚧  Catalog 🚀 Under construction...  🚧
 </h4>

<hr>

<p align="center">
  <img alt="Github top language" src="https://img.shields.io/github/languages/top/jocile/catalog?color=56BEB8">

  <img alt="Github language count" src="https://img.shields.io/github/languages/count/jocile/catalog?color=56BEB8">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/jocile/catalog?color=56BEB8">

  <img alt="License" src="https://img.shields.io/github/license/jocile/catalog?color=56BEB8">

  <img alt="Github issues" src="https://img.shields.io/github/issues/jocile/catalog?color=56BEB8" />

  <img alt="Github forks" src="https://img.shields.io/github/forks/jocile/catalog?color=56BEB8" />

  <img alt="Github stars" src="https://img.shields.io/github/stars/jocile/catalog?color=56BEB8" />
</p>

<p align="center">
  <a href="#dart-about">About</a> &#xa0; | &#xa0; 
  <a href="#sparkles-features">Features</a> &#xa0; | &#xa0;
  <a href="#rocket-technologies">Technologies</a> &#xa0; | &#xa0;
  <a href="#white_check_mark-requirements">Requirements</a> &#xa0; | &#xa0;
  <a href="#checkered_flag-starting">Starting</a> &#xa0; | &#xa0;
  <a href="#books-references">References</a> &#xa0; | &#xa0;
  <a href="#memo-license">License</a> &#xa0; | &#xa0;
  <a href="https://github.com/jocile" target="_blank">Author</a>
</p>

<br>

</div>

## :dart: About

Product catalog system using spring boot as backend and react as frontend, which is developed in [DevSuperior](https://devsuperior.com.br/) course bootcamp.

## :sparkles: Features

- Product catalog screen with pagination;
- Product detail screen;
- Interface for listing, inserting, editing and deleting records.
- Security with data validation, authentication and authorization access;
- CRUD Database access for products, categories and users;
- Automated tests;
- Cloud Services Platforms;

## :rocket: Technologies

The following tools were used in this project:

- [Java JDK 17](https://docs.oracle.com/en/java/javase/17/);
  - [Lombok to generate class constructors, with getters and setters](https://projectlombok.org/);
  - [Maven builder](https://maven.apache.org/);
  - [Spring Boot framework](https://glysns.gitbook.io/springframework/);
    - [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-jpa-and-spring-data);
  - [Swagger implementing springdoc-openapi](https://springdoc.org/);
- [H2 in-memory database](https://www.h2database.com/);
- [Postgresql 12 database](https://www.postgresql.org/about/news/postgresql-12-released-1976/);
  - [PgAdmin database administration platform](https://www.pgadmin.org/);
- [ReactJS user interfaces](https://pt-br.reactjs.org/);
  - [Yarn - package management](https://yarnpkg.com/);
  - [React Native](https://reactnative.dev/);
  - [TypeScript](https://www.typescriptlang.org/);
  - [Bootstrap web framework](https://getbootstrap.com/);
- [Netlify web app server](https://www.netlify.com/);
- [Heroku - plataform as a service](https://www.heroku.com/);
- [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-welcome.html);

## :white_check_mark: Requirements

Before starting :checkered_flag:, you need to have [Git](https://git-scm.com), [yarn](https://yarnpkg.com/) and [Java 17](https://docs.oracle.com/en/java/javase/17/) installed.

## :checkered_flag: Starting

```bash
# Clone this project
$ git clone https://github.com/jocile/catalog

# Access
$ cd catalog/frontend

# Install dependencies
$ yarn

# Run the project frontend
$ yarn start
```

> The local frontend server will initialize in the browser: <http://localhost:3000>\
> and Dashboard URL route will be initialized in: <http://localhost:3000/dashboard>

```bash
# Access the backend server with the following
$ cd ../backend

# Run the backend interface with
$ ./mvnw spring-boot:run
```

> The local backend server will initialize in the browser: <http://localhost:8080/swagger-ui/index.html>\
> and in-memory database H2 will be initialized in <http://localhost:8080/h2-console>

## :books: References

:books: [See the documentation on wiki](https://github.com/jocile/catalog/wiki)

## :memo: License

This project is under license from MIT. For more details, see the [LICENSE](LICENSE.md) file.

Made with :heart: by <a href="https://github.com/jocile" target="_blank">Jocile</a>

&#xa0;

<a href="#top">Back to top</a>
