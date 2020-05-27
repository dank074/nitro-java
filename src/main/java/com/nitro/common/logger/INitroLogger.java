package com.nitro.common.logger;

public interface INitroLogger {

    void log(String message);
    void error(String message);
    void warn(String message);
    void setName(String name);
    void setDescription(String description);
    void setPrint(boolean flag);
}
