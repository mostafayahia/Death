/*
 * Copyright (C) 2018 Yahia H. El-Tayeb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package free.elmasry.death;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TemplateActivity extends Activity {

    public static final String EXTRA_KEY_DATA = "free.elmasry.death.extra.KEY_DATA";

    public static final int EXTRA_VALUE_BENEFIT_OF_PRAYER = 100;
    public static final int EXTRA_VALUE_HOW_TO_PRAY = 101;
    public static final int EXTRA_VALUE_DOAA_WRITTEN = 102;
    public static final int EXTRA_VALUE_INVALID_VALUE = -1;

    /**
     * run at first time the activity created
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // set the root view
        setContentView(R.layout.prayer);

        // get the views resides in the root view
        TextView templateTxt = (TextView) findViewById(R.id.templateTxt);

        final int extraValue = getIntent().getIntExtra(TemplateActivity.EXTRA_KEY_DATA, EXTRA_VALUE_INVALID_VALUE);

        if (extraValue == EXTRA_VALUE_INVALID_VALUE) {
            Toast.makeText(this, R.string.error_happened, Toast.LENGTH_LONG)
                    .show();
            return;
        }

        switch (extraValue) {
            case EXTRA_VALUE_BENEFIT_OF_PRAYER:
                templateTxt.setText(getString(R.string.prayer_benefit));
                break;
            case EXTRA_VALUE_HOW_TO_PRAY:
                templateTxt.setText(getString(R.string.how_to_pray));
                break;
            case EXTRA_VALUE_DOAA_WRITTEN:
                templateTxt.setText(getString(R.string.doaa_written));
                break;
        }

    }

}
