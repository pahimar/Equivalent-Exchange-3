package net.minecraftforge.client.model.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Vertex {

    private float x;
    private float y;
    private float z;

    public Vertex() {

    }

    public Vertex(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex(int i, int j) {

        setX(i);
        setY(j);
    }

    public Vertex(Vertex position) {

        setX(position.getX());
        setY(position.getY());
        setZ(position.getZ());
    }

    public float getX() {

        return x;
    }

    public void setX(float x) {

        this.x = x;
    }

    public float getY() {

        return y;
    }

    public void setY(float y) {

        this.y = y;
    }

    public float getZ() {

        return z;
    }

    public void setZ(float z) {

        this.z = z;
    }

    public double norm() {

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public void normalize() {

        double norm = norm();

        setX(getX() / (float) norm);
        setY(getY() / (float) norm);
        setZ(getZ() / (float) norm);
    }

    public double distanceFrom(Vertex to) {

        return Math.sqrt(this.getX() * to.getX() + this.getY() + to.getY() + this.getZ() * to.getZ());
    }

    public Vertex rotateZ(double angle) {

        float savedX = getX();
        x = (float) (x * Math.cos(angle) + y * Math.sin(angle));
        y = (float) (savedX * -Math.sin(angle) + y * Math.cos(angle));

        return this;

    }

    public Vertex rotateX(double angle) {

        // Rotation matrix on X is
        //		1		0		0
        //		0	 cos(x)	sin(x)
        //		0	-sin(x) cos(x)
        float savedY = y;
        y = (float) (y * Math.cos(angle) + z * -Math.sin(angle));
        z = (float) (savedY * Math.sin(angle) + z * Math.cos(angle));
        return this;

    }

    public Vertex copyAndRotateZ(float angle) {

        float newX = (float) (x * Math.cos(angle) + y * Math.sin(angle));
        float newY = (float) (x * -Math.sin(angle) + y * Math.cos(angle));

        return new Vertex(newX, newY, z);

    }

    public void add(Vertex offSet) {

        x += offSet.getX();
        y += offSet.getY();
        z += offSet.getZ();
    }

    public Vertex copyAndAdd(Vertex offSet) {

        return new Vertex(getX() + offSet.getX(), getY() + offSet.getY(), getZ() + offSet.getZ());
    }

    public Vertex mult(Vertex offSet) {

        return new Vertex(getX() * offSet.getX(), getY() * offSet.getY(), getZ() * offSet.getZ());
    }

    public Vertex mult(double factor) {

        return mult((float) factor);
    }

    public Vertex mult(float factor) {

        return new Vertex(getX() * factor, getY() * factor, getZ() * factor);
    }

    public Vertex copyAndSub(Vertex v) {

        // TODO Auto-generated method stub
        return new Vertex(getX() - v.getX(), getY() - v.getY(), getZ() - v.getZ());
    }

    public Vertex copyAndMult(float coef) {

        // TODO Auto-generated method stub
        return new Vertex(getX() * coef, getY() * coef, getZ() * coef);
    }

    public float dot(Vertex v) {

        return v.x * x + v.y * y;//+ v.z * z;
    }

    public float perpDot(Vertex v) {

        return x * v.y - y * v.x;
    }

    public void subFrom(Vertex position) {

        setX(position.getX() - getX());
        setY(position.getY() - getY());
        setZ(position.getZ() - getZ());
    }

    @Override
    public String toString() {

        return "x=" + x + ",y=" + y + ",z=" + z;
    }
}
