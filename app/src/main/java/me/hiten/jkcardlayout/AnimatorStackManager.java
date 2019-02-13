package me.hiten.jkcardlayout;


import java.util.Random;
import java.util.Stack;

public class AnimatorStackManager {


    private Stack<AnimatorInfo> mStack = new Stack<>();


    Opt mPendingOpt = Opt.NONE;

    enum  Opt{
        NONE,REMOVE,ADD;
    }

    public void setPendingOpt(Opt pendingOpt) {
        this.mPendingOpt = pendingOpt;
    }

    public Opt getPendingOpt() {
        return mPendingOpt;
    }

    public AnimatorInfo takeAdd() {
        AnimatorInfo pop = mStack.pop();
        if (pop == null) {
            pop = new AnimatorInfo();
        }
        if (!pop.isAdd) {
            pop.revert();
        }
        return pop;
    }


    public void addRemoveToBackStack(AnimatorInfo animatorInfo) {
        if (!animatorInfo.isAdd) {
            animatorInfo.revert();
        }
        mStack.push(animatorInfo);
    }

    public AnimatorInfo createRemove() {
        AnimatorInfo animatorInfo = new AnimatorInfo();
        animatorInfo.isAdd = false;
        animatorInfo.targetX=0;
        animatorInfo.targetY = 0;
        animatorInfo.endRotation = 0;
        boolean b = new Random().nextBoolean();
        animatorInfo.startX = b?-1.0f:1.0f;
        animatorInfo.startY = 1f;
        animatorInfo.startRotation = 10;
        return animatorInfo;
    }


    public static class AnimatorInfo {
        boolean isAdd;
        float startX;
        float startY;
        float targetX;
        float targetY;
        float startRotation;
        float endRotation;

        void revert() {
            float tempX = startX;
            startX = targetX;
            targetX = tempX;

            float tempY = startY;
            startY = targetY;
            targetY = tempY;

            float tempRotation = startRotation;
            startRotation = endRotation;
            endRotation = tempRotation;
            isAdd = !isAdd;
        }
    }

}
