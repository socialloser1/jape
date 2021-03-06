/*
  Written 1999 by Douglas Greiman.
 
  This software may be used and distributed according to the terms
  of the GNU Public License, incorporated herein by reference.
*/

package duggelz.jape;

class IntField implements Field
{
    private int offset;

    public IntField(int offset) 
    {
	this.offset = offset;
    }

    public String get(byte[] data)
    {
	return Integer.toString(this.getInt(data));
    }

    public int getInt(byte[] data)
    {
	int value = ( (((int) data[this.offset]) & 0xFF) | 
		      ((((int) data[this.offset + 1]) & 0xFF) << 8) |
		      ((((int) data[this.offset + 2]) & 0xFF) << 16) |
		      ((((int) data[this.offset + 3]) & 0xFF) << 24) );
	return value;
    }

    public void set(byte[] data, String str) throws NumberFormatException
    {
	int value = Integer.parseInt(str);
	this.setInt(data, value);
    }

    public void setInt(byte[] data, int value)
    {
	data[this.offset] = (byte) (value & 0xFF);
	data[this.offset + 1] = (byte) ((value >>> 8) & 0xFF);
	data[this.offset + 2] = (byte) ((value >>> 16) & 0xFF);
	data[this.offset + 3] = (byte) ((value >>> 24) & 0xFF);
    }
}
    
