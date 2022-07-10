# Diagrams

## Class diagram

```mermaid
classDiagram
    class Category{
        <<entity>>
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        - id : Long
        - name : String
    }
    class CategoryRepository{
      <<repository-interface>>
    }
    class CategoryResource{
      <<controller>>
      findAll() List~Category~
    }
    class CategoryService{
      <<service>>
      - repository : CategoryRepository
      findAll() List~Category~
    }
    CategoryService *-- Category
    CategoryService *-- CategoryRepository
    CategoryResource *-- Category
  ```
## ER diagram

```mermaid
erDiagram
    tb_category {
        BIGINT id PK
        CHARACTER name
    }
```

## References

:link: [Diagrams wiki](https://github.com/jocile/catalog/wiki/Diagrams)\
:memo: [Mermaid diagrams](https://mermaid-js.github.io/)\
:chart_with_upwards_trend: [Diagrams online editor](https://mermaid-js.github.io/mermaid-live-editor/edit#pako:eNpVkM1qw0AMhF9F6NRC_AI-FBo7zSXQQHLz-iC8cnZJ9oe1TAm2373rmkKrk9B8MwyasAuascRbomjgWisPed6byiQ7iKOhhaJ4m48s4ILn5wz7l2OAwYQYrb-9bvx-haCaTivGIMb6-7JJ1Y__0_MMdXOiKCG2f5XrV5jh0NizyfH_FZM4uz6ansqeio4SVJRa3KHj5MjqXHtaDQrFsGOFZV419zQ-RKHyS0bHqEn4oK2EhDnmMfAOaZRwefoOS0kj_0K1pfwFtx2XbzAdW4g)
