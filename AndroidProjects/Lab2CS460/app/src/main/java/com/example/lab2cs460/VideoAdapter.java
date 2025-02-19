package com.example.lab2cs460;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    private List<VideoItem> videoItems;

    public VideoAdapter(List<VideoItem> videoItem) {
        this.videoItems = videoItem;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));

    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }


    static class VideoViewHolder extends RecyclerView.ViewHolder{

        TextView textVideoTitle1, textVideoDescription1, textVideoID1;

        VideoView videoView;

        ProgressBar progressBar;

            public VideoViewHolder(@NonNull View itemView) {
                super(itemView);
                videoView = itemView.findViewById(R.id.videoView);
                textVideoTitle1 = itemView.findViewById(R.id.textVideoTitle);
                textVideoDescription1 = itemView.findViewById(R.id.textVideoDescription);
                textVideoID1 = itemView.findViewById(R.id.textVideoID);
                progressBar = itemView.findViewById(R.id.videoProgressBar);
            }


            void setVideoData(VideoItem videoItem){
                textVideoTitle1.setText(videoItem.videoTitle);
                textVideoDescription1.setText(videoItem.videoDescription);
                textVideoID1.setText(videoItem.videoID);
                videoView.setVideoPath(videoItem.videoURL);

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        progressBar.setVisibility(View.GONE);
                        mp.start();

                        float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                        float screenRatio = videoView.getWidth()/(float) videoView.getHeight();

                        float scale = videoRatio / screenRatio;
                        if(scale >=1f){
                            videoView.setScaleX(scale);
                        }else{
                            videoView.setScaleY(1f / scale);
                        }
                    }

                });

                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }

    }



}
