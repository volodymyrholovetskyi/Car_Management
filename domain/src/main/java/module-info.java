module domain {
    opens com.holovetskyi.car to com.google.gson, app;
    exports com.holovetskyi.car;
    exports com.holovetskyi.car.type;
    requires lombok;
    requires validator;
}
