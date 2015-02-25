package com.trywildcard.pair.model.action;

import java.net.URL;
import java.util.List;

/**
 * Created by karthiksenthil on 2/25/15.
 */
public interface Action {

        public String getName();

        public ActionType getMethod();

        public URL getUrl();

        public List<String> getRequiredParameters();

        public List<String> getOptionalParameters();

        public enum ActionType {
            GET, POST, DELETE
        }

}
