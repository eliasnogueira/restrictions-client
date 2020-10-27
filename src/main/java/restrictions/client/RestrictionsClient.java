/*
 * MIT License
 *
 * Copyright (c) 2020 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package restrictions.client;

import static io.restassured.RestAssured.given;

import client.RestClient;
import config.ConfigurationManager;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

public class RestrictionsClient extends RestClient {

    public static final String BASE_URI = ConfigurationManager.getConfiguration().baseUri();
    public static final String BASE_PATH = ConfigurationManager.getConfiguration().basePath();
    public static final String PORT = String.valueOf(ConfigurationManager.getConfiguration().port());

    public static final String VERSION = "/v1";
    public static final String RESTRICTIONS = "/restrictions";

    public RestrictionsClient() {
        super(BASE_URI, PORT, BASE_PATH, VERSION);
    }

    public ValidatableResponse returnsSocialSecurityNumberWithRestriction(String cpf) {
        return
            given().
                pathParam("cpf", cpf).
            when().
                get(getPath(RESTRICTIONS + SLASH + "{cpf}")).
            then().
                statusCode(HttpStatus.SC_OK);
    }

    public void searchSocialSecurityNumberWithoutRestriction(String cpf) {
        given().
            pathParam("cpf", cpf).
        when().
            get(getPath(RESTRICTIONS + SLASH + "{cpf}")).
        then().
            statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
