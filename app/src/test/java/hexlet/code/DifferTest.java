package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    @Test
    void jsonStylishTest() throws Exception {
        Parser.parse("src/test/resources/file2.json");

        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "stylish");

        assertThat(result)
                .isEqualTo("""
                        {
                            chars1: [a, b, c]
                          - chars2: [d, e, f]
                          + chars2: false
                          - checked: false
                          + checked: true
                          - default: null
                          + default: [value1, value2]
                          - id: 45
                          + id: null
                          - key1: value1
                          + key2: value2
                            numbers1: [1, 2, 3, 4]
                          - numbers2: [2, 3, 4, 5]
                          + numbers2: [22, 33, 44, 55]
                          - numbers3: [3, 4, 5]
                          + numbers4: [4, 5, 6]
                          + obj1: {nestedKey=value, isNested=true}
                          - setting1: Some value
                          + setting1: Another value
                          - setting2: 200
                          + setting2: 300
                          - setting3: true
                          + setting3: none
                        }
                                                """);
    }

    @Test
    void ymlStylishTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.yml",
                "src/test/resources/file2.yml",
                "stylish");

        assertThat(result)
                .isEqualTo("""
                        {
                            follow: false
                          - host: hexlet.io
                          + ip: 192.168.0.1
                            key: [string1, string2, string3, string4, string5, string6]
                            proxy: 123.234.53.22
                          - root: {tree={key1=11, key2=22}}
                          + root: {tree={key1=33, key2=22}}
                          - timeout: 50
                          + timeout: 70
                        }
                        """);
    }

    @Test
    void jsonPlainTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "plain");

        assertThat(result)
                .isEqualTo("""
                        Property 'chars2' was updated. From [complex value] to false
                        Property 'checked' was updated. From false to true
                        Property 'default' was updated. From null to [complex value]
                        Property 'id' was updated. From 45 to null
                        Property 'key1' was removed
                        Property 'key2' was added with value: 'value2'
                        Property 'numbers2' was updated. From [complex value] to [complex value]
                        Property 'numbers3' was removed
                        Property 'numbers4' was added with value: [complex value]
                        Property 'obj1' was added with value: [complex value]
                        Property 'setting1' was updated. From 'Some value' to 'Another value'
                        Property 'setting2' was updated. From 200 to 300
                        Property 'setting3' was updated. From true to 'none'
                                                """);
    }

    @Test
    void jsonJsonTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "json");
        assertThat(result)
                .isEqualTo("""
                        {
                          "chars1" : {
                            "difference" : "equal",
                            "value" : [ "a", "b", "c" ]
                          },
                          "chars2" : {
                            "difference" : "updated",
                            "old_value" : [ "d", "e", "f" ],
                            "new_value" : false
                          },
                          "checked" : {
                            "difference" : "updated",
                            "old_value" : false,
                            "new_value" : true
                          },
                          "default" : {
                            "difference" : "updated",
                            "old_value" : null,
                            "new_value" : [ "value1", "value2" ]
                          },
                          "id" : {
                            "difference" : "updated",
                            "old_value" : 45,
                            "new_value" : null
                          },
                          "key1" : {
                            "difference" : "removed",
                            "value" : "value1"
                          },
                          "key2" : {
                            "difference" : "added",
                            "value" : "value2"
                          },
                          "numbers1" : {
                            "difference" : "equal",
                            "value" : [ 1, 2, 3, 4 ]
                          },
                          "numbers2" : {
                            "difference" : "updated",
                            "old_value" : [ 2, 3, 4, 5 ],
                            "new_value" : [ 22, 33, 44, 55 ]
                          },
                          "numbers3" : {
                            "difference" : "removed",
                            "value" : [ 3, 4, 5 ]
                          },
                          "numbers4" : {
                            "difference" : "added",
                            "value" : [ 4, 5, 6 ]
                          },
                          "obj1" : {
                            "difference" : "added",
                            "value" : {
                              "nestedKey" : "value",
                              "isNested" : true
                            }
                          },
                          "setting1" : {
                            "difference" : "updated",
                            "old_value" : "Some value",
                            "new_value" : "Another value"
                          },
                          "setting2" : {
                            "difference" : "updated",
                            "old_value" : 200,
                            "new_value" : 300
                          },
                          "setting3" : {
                            "difference" : "updated",
                            "old_value" : true,
                            "new_value" : "none"
                          }
                        }""");
    }
}
