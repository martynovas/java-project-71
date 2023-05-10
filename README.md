# Вычислитель отличий – программа, определяющая разницу между двумя структурами данных.
Возможности утилиты:
 - Поддержка разных входных форматов: yaml и json
 - Генерация отчета в виде plain text, stylish и json
Пример использования:

# Формат plain
```
./app --format plain path/to/file.yml another/path/file.json

Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed
```
# Формат stylish
```
./app filepath1.json filepath2.json

{
+ follow: false
+ numbers: [1, 2, 3]
  setting1: Value 1
- setting2: 200
- setting3: true
+ setting3: {key=value}
+ setting4: blah blah
  }
  ```
### Hexlet tests and linter status:
[![Actions Status](https://github.com/martynovas/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/martynovas/java-project-71/actions)

### Codeclimate Maintainability
[![Maintainability](https://api.codeclimate.com/v1/badges/3b6ee5dab491f47acc9e/maintainability)](https://codeclimate.com/github/martynovas/java-project-71/maintainability)

### Codeclimate Test Coverage
[![Test Coverage](https://api.codeclimate.com/v1/badges/3b6ee5dab491f47acc9e/test_coverage)](https://codeclimate.com/github/martynovas/java-project-71/test_coverage)

### Asciinema
[![asciicast](https://asciinema.org/a/byVV4bF5QCXd4Y12lhhamEPXp.svg)](https://asciinema.org/a/byVV4bF5QCXd4Y12lhhamEPXp)