package com.apppartner.androidprogrammertest.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.models.ChatData;
import com.apppartner.androidprogrammertest.util.ImageTranformer;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatsArrayAdapter extends ArrayAdapter<ChatData> {
    Context mContext;

    public ChatsArrayAdapter(Context context, List<ChatData> objects) {
        super(context, 0, objects);
        mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ChatCell chatCell = new ChatCell();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(R.layout.cell_chat, parent, false);

        chatCell.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
        chatCell.messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        chatCell.avatarImage = (ImageView) convertView.findViewById(R.id.imageView);

        ChatData chatData = getItem(position);

        chatCell.usernameTextView.setText(chatData.username);
        chatCell.messageTextView.setText(chatData.message);

        String nameFontPath = "fonts/Jelloween - Machinato.ttf";
        String messageFontPath = "fonts/Jelloween - Machinato Light.ttf";
        Typeface nameTf = Typeface.createFromAsset(mContext.getAssets(), nameFontPath);
        Typeface messageTf = Typeface.createFromAsset(mContext.getAssets(), messageFontPath);
        chatCell.usernameTextView.setTypeface(nameTf);
        chatCell.messageTextView.setTypeface(messageTf);

        Picasso.with(mContext).load(chatData.avatarURL).transform(new ImageTranformer()).resize(200, 200).into(chatCell.avatarImage);

        return convertView;
    }

    private static class ChatCell {
        TextView usernameTextView;
        TextView messageTextView;
        ImageView avatarImage;
    }
}
