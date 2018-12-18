package com.codearms.maoqiqi.skin.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于添加、删除实现皮肤监听接口的类和提供一个方法用于调用所有类(实现皮肤监听接口)执行{@link SkinObserver#updateSkin()}方法
 *
 * @author Doc.March
 * @date 2017/6/8
 */
public class SkinObservable {

    /**
     * 保存所有实现SkinObserver接口的实现类
     */
    private final List<SkinObserver> skinObservers;

    public SkinObservable() {
        this.skinObservers = new ArrayList<>();
    }

    /**
     * 添加一个实现皮肤监听接口的类
     *
     * @param observer 实现皮肤监听接口的类
     */
    public synchronized void addObserver(SkinObserver observer) {
        if (!skinObservers.contains(observer)) {
            skinObservers.add(observer);
        }
    }

    /**
     * 删除一个实现皮肤监听接口的类
     *
     * @param observer 实现皮肤监听接口的类
     */
    public synchronized void removeObserver(SkinObserver observer) {
        skinObservers.remove(observer);
    }

    /**
     * 调用所有实现SkinObserver接口执行{@link SkinObserver#updateSkin()}方法
     */
    public void notifyUpdateSkin() {
        if (skinObservers.size() == 0) return;
        for (SkinObserver observer : skinObservers) {
            if (observer != null) observer.updateSkin();
        }
    }
}