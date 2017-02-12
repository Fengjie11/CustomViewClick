package fengjie.baway.com.customviewclick;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by FengJIe on 2017/2/12.
 */

public class MyView extends View {

    private Path path;
    private Region region;
    private Paint paint;
    private Paint paint1;
    private Path path1;
    private Region region1;
    private int r;
    private String toas;

    public MyView(Context context) {
        super(context);


    }

    private void initPains() {
        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setStrokeWidth(10f);
        paint1.setStyle(Paint.Style.FILL);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10f);
        paint.setStyle(Paint.Style.FILL);

        path1 = new Path();
        path = new Path();
        region1 = new Region();
        region = new Region();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.addCircle(w/2,h/2,r,Path.Direction.CW);
        path1.addCircle(w/2,h/2,150,Path.Direction.CW);

        Region region2=new Region(-w,-h,w,h);
        region.setPath(path,region2);
        region1.setPath(path1,region2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (region1.contains(x,y)){
                    Toast.makeText(getContext(),"点击小圆",Toast.LENGTH_SHORT).show();
                }else if (region.contains(x,y)){
                    Toast.makeText(getContext(),toas,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"空白处",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path circle=path;
        canvas.drawPath(circle,paint);


        Path minCircle=path1;
        canvas.drawPath(minCircle,paint1);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        initPains();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        r = typedArray.getInteger(R.styleable.MyView_r, 100);
        toas = typedArray.getString(R.styleable.MyView_toast);
    }

}
