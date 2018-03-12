/*
 * El-Masry
 * Free application
 * this is a simple application for death prayer
 * and Doaa for the Muslim dead people
 */

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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DeathEntryActivity extends Activity implements OnClickListener {

    /**
     * run at the first run of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the root view for this activity
        setContentView(R.layout.death_entry);

        // get the views in this activity
        Button benefitBtn = (Button) findViewById(R.id.benefitBtn);
        Button prayBtn = (Button) findViewById(R.id.prayBtn);
        Button doaaBtn = (Button) findViewById(R.id.listenDoaaBtn);
        Button doaaWrittenBtn = (Button) findViewById(R.id.doaaWrittenBtn);

        // set event handlers for this buttons
        benefitBtn.setOnClickListener(this);
        prayBtn.setOnClickListener(this);
        doaaBtn.setOnClickListener(this);
        doaaWrittenBtn.setOnClickListener(this);
    }

    /* event handler for the buttons */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.benefitBtn:
                intent = new Intent(this, TemplateActivity.class);
                intent.putExtra(TemplateActivity.EXTRA_KEY_DATA, TemplateActivity.EXTRA_VALUE_BENEFIT_OF_PRAYER);
                startActivity(intent);
                break;
            case R.id.prayBtn:
                intent = new Intent(this, TemplateActivity.class);
                intent.putExtra(TemplateActivity.EXTRA_KEY_DATA, TemplateActivity.EXTRA_VALUE_HOW_TO_PRAY);
                startActivity(intent);
                break;
            case R.id.doaaWrittenBtn:
                intent = new Intent(this, TemplateActivity.class);
                intent.putExtra(TemplateActivity.EXTRA_KEY_DATA, TemplateActivity.EXTRA_VALUE_DOAA_WRITTEN);
                startActivity(intent);
                break;
            case R.id.listenDoaaBtn:
            default:
                intent = new Intent(this, ListenDoaaActivity.class);
                startActivity(intent);
                break;
        }

    }

}
