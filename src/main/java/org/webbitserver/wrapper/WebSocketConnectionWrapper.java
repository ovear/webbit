package org.webbitserver.wrapper;

import org.webbitserver.CometConnection;
import org.webbitserver.HttpRequest;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

public class WebSocketConnectionWrapper implements CometConnection {

    private CometConnection connection;

    public WebSocketConnectionWrapper(CometConnection connection) {
        this.connection = connection;
    }

    public CometConnection underlyingControl() {
        return connection;
    }

    public WebSocketConnectionWrapper underlyingControl(CometConnection control) {
        this.connection = control;
        return this;
    }

    public CometConnection originalControl() {
        if (connection instanceof WebSocketConnectionWrapper) {
            WebSocketConnectionWrapper wrapper = (WebSocketConnectionWrapper) connection;
            return wrapper.originalControl();
        } else {
            return connection;
        }
    }

    @Override
    public HttpRequest httpRequest() {
        return connection.httpRequest();
    }

    @Override
    public WebSocketConnectionWrapper send(String message) {
        connection.send(message);
        return this;
    }

    @Override
    public WebSocketConnectionWrapper close() {
        connection.close();
        return this;
    }

    @Override
    public Map<String, Object> data() {
        return connection.data();
    }

    @Override
    public Object data(String key) {
        return connection.data(key);
    }

    @Override
    public WebSocketConnectionWrapper data(String key, Object value) {
        connection.data(key, value);
        return this;
    }

    @Override
    public Set<String> dataKeys() {
        return connection.dataKeys();
    }

    @Override
    public Executor handlerExecutor() {
        return connection.handlerExecutor();
    }

    @Override
    public void execute(Runnable command) {
        connection.execute(command);
    }

}
