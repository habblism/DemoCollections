package yhb.dc.demo.customview.click_effect;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import yhb.dc.R;
import yhb.dc.common.Demo;

@Demo(id = Demo.DEMO_ID_CLICK_EFFECTS, name = "常见的点击效果演示")
public class CommonButtonClickEffectActivity extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_button_click_effect);

        final ImageView imageView = findViewById(R.id.image_button_animated_vector_drawable);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((AnimatedVectorDrawable) imageView.getDrawable()).start();
                }
            }
        });
    }
}
