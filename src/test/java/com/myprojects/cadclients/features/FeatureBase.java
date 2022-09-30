package com.myprojects.cadclients.features;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public abstract class FeatureBase<R> {

    @Autowired
    protected TestRestTemplate template;

    private String servletPath = "/rest/clients";

    @LocalServerPort
    protected int port;

    @Autowired
    protected ModelMapper mapper;

    @Autowired
    protected R repository;

    protected ResponseEntity<?> expectedResponseEntity;

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

    protected <RP, RQ> ResponseEntity<RP> requestCreate(String resource, RQ createRequest, Class<RP> responseClass) {
        HttpEntity<RQ> body = new HttpEntity<RQ>(createRequest, this.getHeaders());
        String url = String.format("http://localhost:%d%s/%s", port, servletPath, resource);
        return template.exchange(url, HttpMethod.POST, body, responseClass);
    }

    protected <RP, RQ, TID> ResponseEntity<RP> requestUpdate(String resource, TID id, RQ createRequest, Class<RP> responseClass) {
        HttpEntity<RQ> body = new HttpEntity<RQ>(createRequest, this.getHeaders());
        String url = String.format("http://localhost:%d%s/%s/%s", port, servletPath, resource, id);
        return template.exchange(url, HttpMethod.PUT, body, responseClass);
    }

    protected <RP, RQ, TID> ResponseEntity<RP> requestUpdatePartial(String resource, TID id, RQ createRequest, Class<RP> responseClass) {
        HttpEntity<RQ> body = new HttpEntity<RQ>(createRequest, this.getHeaders());
        String url = String.format("http://localhost:%d%s/%s/%s", port, servletPath, resource, id);
        return template.exchange(url, HttpMethod.PATCH, body, responseClass);
    }

    protected <TID> ResponseEntity<Void> requestDelete(String resource, TID id) {
        HttpEntity<Void> body = new HttpEntity<Void>(this.getHeaders());
        String url = String.format("http://localhost:%d%s/%s/%s", port, servletPath, resource, id);
        return template.exchange(url, HttpMethod.DELETE, body, Void.class);
    }

    protected <RP, TID> ResponseEntity<RP> requestFindById(String resource, TID id, Class<RP> responseClass) {
        HttpEntity<RP> body = new HttpEntity<RP>(this.getHeaders());
        String url = String.format("http://localhost:%d%s/%s/%s", port, servletPath, resource, id);
        return template.exchange(url, HttpMethod.GET, body, responseClass);
    }

    protected <RP> ResponseEntity<RP> requestFind(String resource, Class<RP> responseClass) {
        HttpEntity<RP> body = new HttpEntity<RP>(this.getHeaders());
        String url = String.format("http://localhost:%d%s/%s", port, servletPath, resource);
        return template.exchange(url, HttpMethod.GET, body, responseClass);
    }

    @SuppressWarnings("rawtypes")
    protected <T> void setPropertyValue(T obj, String fieldName, Object fieldValue) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        List<String> listFields = Arrays.asList(fieldName.split("\\."));
        Class<?> originClass = obj.getClass();
        Field field;
        Method method;
        Object object = obj;

        for (int i = 0; i < listFields.size(); i++) {
            originClass = object.getClass();

            if (i + 1 == listFields.size()) {
                field = originClass.getDeclaredField(listFields.get(i));
                method = originClass.getDeclaredMethod("set" + StringUtils.capitalize(field.getName()), field.getType());

                method.invoke(object, toObject(field.getType(), fieldValue));
            } else {
                method = originClass.getDeclaredMethod("get" + StringUtils.capitalize(listFields.get(i)));
                object = method.invoke(object);

                if (object instanceof Collection) {
                    Collection collection = (Collection) object;
                    object = collection.stream().findFirst().get();
                }
            }
        }
    }

    private <T> Object toObject(Class<T> clazz, Object value) {
        if (value != null) {
            if (Boolean.class == clazz) return Boolean.parseBoolean((String) value);
            if (Integer.class == clazz) return Integer.parseInt((String) value);
            if (Long.class == clazz) return Long.parseLong((String) value);
            if (BigDecimal.class == clazz) return new BigDecimal((String) value);
        }
        return value;
    }
}