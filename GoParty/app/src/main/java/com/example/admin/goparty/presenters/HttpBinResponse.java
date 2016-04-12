package com.example.admin.goparty.presenters;

import java.util.Map;

public class HttpBinResponse {
    // the request url
    String url;

    // the requester ip
    String origin;

    // all headers that have been sent
    Map headers;

    // url arguments
    Map args;

    // post form parameters
    Map form;

    // post body json
    Map json;
}
