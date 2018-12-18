package com.codearms.maoqiqi.skin.demo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codearms.maoqiqi.skin.manager.SkinManager;
import com.codearms.maoqiqi.skin.manager.SkinPreferencesManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_color_picker, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.menu_color_picker) {
            createDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createDialog() {
        final String[] colors = new String[]{"默认(蓝色主题)", "绿色主题", "红色主题", "日间主题", "夜间主题"};
        final String[] suffixNames = new String[]{"", "_green", "_red", "skins/skin-day-debug.apk", "skins/skin-night-debug.apk"};
        new AlertDialog.Builder(this)
                .setTitle("选择主题")
                .setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                            case 1:
                            case 2:
                                if (SkinPreferencesManager.getInstance().isBuiltInSkin() &&
                                        SkinPreferencesManager.getInstance().getSkinSuffixName().equals(suffixNames[which])) {
                                    Toast.makeText(BaseActivity.this, "正在使用【" + colors[which] + "】皮肤", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                SkinManager.getInstance().loadBuiltInSkin(suffixNames[which]);
                                break;
                            case 3:
                                if (SkinPreferencesManager.getInstance().isAssetsSkin() &&
                                        SkinPreferencesManager.getInstance().getSkinAssetsFilePath().equals(suffixNames[which])) {
                                    Toast.makeText(BaseActivity.this, "正在使用【" + colors[which] + "】皮肤", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                SkinManager.getInstance().loadAssetsSkin(suffixNames[which], "_day");
                                break;
                            case 4:
                                if (SkinPreferencesManager.getInstance().isAssetsSkin() &&
                                        SkinPreferencesManager.getInstance().getSkinAssetsFilePath().equals(suffixNames[which])) {
                                    Toast.makeText(BaseActivity.this, "正在使用【" + colors[which] + "】皮肤", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                SkinManager.getInstance().loadAssetsSkin(suffixNames[which], "_night");
                                break;
                        }
                    }
                })
                .setCancelable(false)
                .create().show();
    }
}
