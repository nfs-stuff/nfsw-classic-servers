package me.leorblx.classicnfsw.core;

public interface RewardCalculator<T extends GameEventResult>
{
    int getReward(T result, RewardType type);
}
