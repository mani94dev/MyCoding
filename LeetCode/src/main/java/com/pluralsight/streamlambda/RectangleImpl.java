package com.pluralsight.streamlambda;

import com.pluralsight.streamlambda.Rectangle;

public class RectangleImpl implements Rectangle {

  private final int x0;
  private final int x1;
  private final int y0;
  private final int y1;

  RectangleImpl(int x0,int x1,int y0,int y1){
    this.x0 = x0;
    this.x1 = x1;
    this.y0 = y0;
    this.y1 = y1;
  }

  @Override
  public boolean isInside(int x, int y) {
    if(x>x0 && x<x1 && y>y0 && y<y1){
      return true;
    }
    return false;
  }
}
