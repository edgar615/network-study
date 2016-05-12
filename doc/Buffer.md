

Buffer: This works with a channel to process data

Java NIO中的Buffer用于和NIO通道进行交互。 写：Buffer -> Channel 读：Channel -> Buffer

使用Buffer读写数据的步骤：

1. Write data into the Buffer
2. Call buffer.flip()
3. Read data out of the Buffer
4. Call buffer.clear() or buffer.compact()

clear()方法会清空整个缓冲区。

compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。

### Buffer类型
Buffer有以下类型

- ByteBuffer
- MappedByteBuffer
- CharBuffer
- DoubleBuffer
- FloatBuffer
- IntBuffer
- LongBuffer
- ShortBuffer

### Buffer的四个属性
- capacity 容量

缓冲区能够容纳的数据元素的最大数量。这一容量在缓冲区创建时被设定，并且永远不能被改变。
作为一个内存块，Buffer有一个固定的大小值，你只能往里写capacity个byte、long，char等类型。
一旦Buffer满了，需要将其清空（通过读数据或者清除数据）才能继续写数据往里写数据。

- position 位置

下一个要被读或写的元素的索引。位置会自动由相应的get( )和put( )函数更新。
当你写数据到Buffer中时，position表示当前的位置。初始的position值为0.当一个byte、long等数据写到Buffer后， position会向前移动到下一个可插入数据的Buffer单元。position最大可为capacity – 1.
当读取数据时，也是从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0. 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置。

- limit 上界

缓冲区的第一个不能被读或写的元素。或者说，缓冲区中现存元素的计数。
在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。
当切换Buffer到读模式时， limit表示你最多能读到多少数据。因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值。换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）

- mark 标记

一个备忘位置。调用mark( )来设定mark = postion。调用reset( )设定position = mark。标记在设定前是未定义的(undefined)。

这四个属性之间总是遵循以下关系： 0 <= mark <= position <= limit <= capacity



### Buffer的方法

### Allocating a Buffer
要想获得一个Buffer对象首先要进行分配

<pre>
ByteBuffer buf = ByteBuffer.allocate(48);
</pre>
<pre>
CharBuffer buf = CharBuffer.allocate(1024);
</pre>

新创建的ByteBuffer格式

<table>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>potision(0)、mark(x)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(10)、capacity(10)</td>
    </tr>
</table>

#### 读取/写入

<pre>
public abstract byte get();
public abstract byte get(int index);
public abstract ByteBuffer put(byte b);
public abstract ByteBuffer put(int index, byte b);
</pre>

对于put()，如果运算会导致位置超出上界，就会抛出BufferOverflowException异常。对于get()，如果位置不小于上界，就会抛出BufferUnderflowException异常。绝对存取不会影响缓冲区的位置属性，但是如果您所提供的索引超出范围（负数或不小于上界），也将抛出IndexOutOfBoundsException异常。

<pre>
buffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
</pre>

<table>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td>H</td>
        <td>e</td>
        <td>l</td>
        <td>l</td>
        <td>o</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>mark(x)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>potision(5)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(10)、capacity(10)</td>
    </tr>
</table>

<pre>
buffer.put(0,(byte)'M').put((byte)'w');
</pre>

<table>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td>M</td>
        <td>e</td>
        <td>l</td>
        <td>l</td>
        <td>o</td>
        <td>w</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>mark(x)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>potision(6)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(10)、capacity(10)</td>
    </tr>
</table>

#### 翻转
我们已经写满了缓冲区，现在我们必须准备将其清空。我们想把这个缓冲区传递给一个通道，以使内容能被全部写出。但如果通道现在在缓冲区上执行get()，那么它将从我们刚刚插入的有用数据之外取出未定义数据。如果我们将位置值重新设为0，通道就会从正确位置开始获取，但是它是怎样知道何时到达我们所插入数据末端的呢？这就是上界属性被引入的目的。上界属性指明了缓冲区有效内容的末端。我们需要将上界属性设置为当前位置，然后将位置重置为0。我们可以人工用下面的代码实现：

<pre>
buffer.limit(buffer.position()).position(0);
</pre>

API提供了flip()函数将一个能够继续添加数据元素的填充状态的缓冲区翻转成一个准备读出元素的释放状态（从写模式切换到读模式）。
调用flip()方法会将position设为0，limit设为之前写的position。

翻转后，buffer变为
<table>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td>M</td>
        <td>e</td>
        <td>l</td>
        <td>l</td>
        <td>o</td>
        <td>w</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>mark(x)、potision(0)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(6)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>capacity(10)</td>
    </tr>
</table>

#### rewind()
rewind()将position设为0，不影响limit，可以重新读取数据。

#### 释放
布尔函数hasRemaining()会在释放缓冲区时告诉您是否已经达到缓冲区的上界。

<pre>
for (int i = 0; buffer.hasRemaining( ), i++) {
    myByteArray [i] = buffer.get( );
}
</pre>

一旦缓冲区对象完成填充并释放，它就可以被重新使用了。Clear()函数将缓冲区重置为空状态。它并不改变缓冲区中的任何数据元素，而是仅仅将上界设为容量的值，并把位置设回0

#### 清除 clear()
clear()方法会清空整个缓冲区。

*填充和释放缓冲区*

<pre>
public class BufferFillDrain {

    private static int index = 0;
    private static String[] strings = {"A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly",
            "Help Me! Help Me!"};

    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(100);
        while (fillBuff(buffer)) {
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }

    private static void drainBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println ("");
    }

    private static boolean fillBuff(CharBuffer buffer) {
        if (index >= strings.length) {
            return false;
        }
        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put(string.charAt(i));
        }
        return true;
    }
}
</pre>

#### 压缩 compact()
有时，您可能只想从缓冲区中释放一部分数据，而不是全部，然后重新填充。为了实现这一点，未读的数据元素需要下移以使第一个元素索引为0。尽管重复这样做会效率低下，但这有时非常必要，而API对此为您提供了一个compact()函数。这一缓冲区工具在复制数据时要比您使用get()和put()函数高效得多

*被部分释放的缓冲区*
<table>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td>M</td>
        <td>e</td>
        <td>l</td>
        <td>l</td>
        <td>o</td>
        <td>w</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>mark(x)</td>
        <td></td>
        <td>potision(2)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(6)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>capacity(10)</td>
    </tr>
</table>

调用<code>buffer.compact();</code>后，缓冲区变为
<table>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td>l</td>
        <td>l</td>
        <td>o</td>
        <td>w</td>
        <td>o</td>
        <td>w</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>mark(x)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>potision(4)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(10)、capacity(10)</td>
    </tr>
</table>

您会看到数据元素2-5被复制到0-3位置。位置4和5不受影响，但现在正在或已经超出了当前位置，因此是“死的”。它们可以被之后的put()调用重写。还要注意的是，位置已经被设为被复制的数据元素的数目。也就是说，缓冲区现在被定位在缓冲区中最后一个“存活”元素后插入数据的位置。最后，上界属性被设置为容量的值，因此缓冲区可以被再次填满。
**调用compact()的作用是丢弃已经释放的数据，保留未释放的数据，并使缓冲区对重新填充容量准备就绪**

#### 标记 mark()、reset()
缓冲区的标记在mark( )函数被调用之前是未定义的，调用时标记被设为当前位置的值。reset( )函数将位置设为当前的标记值。如果标记值未定义，调用reset( )将导致InvalidMarkException异常。一些缓冲区函数会抛弃已经设定的标记（rewind( )，clear( )，以及flip( )总是抛弃标记）。如果新设定的值比当前的标记小，调用limit( )或position( )带有索引参数的版本会抛弃标记。

<pre>
buffer.position(2).mark().position(4);
</pre>
后缓冲区变为
<table>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td>M</td>
        <td>e</td>
        <td>l</td>
        <td>l</td>
        <td>o</td>
        <td>w</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td>mark(2)</td>
        <td></td>
        <td>potision(4)</td>
        <td></td>
        <td>limit(6)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>capacity(10)</td>
    </tr>
</table>

如果这个缓冲区现在被传递给一个通道，两个字节（“ow”）将会被发送，而位置会前进到6。如果我们此时调用reset( )，位置将会被设为标记，再次将缓冲区传递给通道将导致四个字节（“llow”）被发送。
<table>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td>M</td>
        <td>e</td>
        <td>l</td>
        <td>l</td>
        <td>o</td>
        <td>w</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td>mark(2)、potision(2)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(6)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>capacity(10)</td>
    </tr>
</table>

#### 比较
可以使用equals()和compareTo()方法比较两个Buffer。
equals()
当满足下列条件时，表示两个Buffer相等：

    有相同的类型（byte、char、int等）。
    Buffer中剩余的byte、char等的个数相等。
    Buffer中所有剩余的byte、char等都相同。

如你所见，equals只是比较Buffer的一部分，不是每一个在它里面的元素都比较。实际上，它只比较Buffer中的剩余元素。

compareTo()方法
compareTo()方法比较两个Buffer的剩余元素(byte、char等)， 如果满足下列条件，则认为一个Buffer“小于”另一个Buffer：

    第一个不相等的元素小于另一个Buffer中对应的元素 。
    所有元素都相等，但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)。

#### 批量移动

<pre>
public ByteBuffer get(byte[] dst)
public ByteBuffer get(byte[] dst, int offset, int length)
public ByteBuffer put(ByteBuffer src)
public ByteBuffer put(byte[] src, int offset, int length)
public final ByteBuffer put(byte[] src)
</pre>

如果您所要求的数量的数据不能被传送，那么不会有数据被传递，缓冲区的状态保持不变，同时抛出BufferUnderflowException异常。因此当您传入一个数组并且没有指定长度，您就相当于要求整个数组被填充。如果缓冲区中的数据不够完全填满数组，您会得到一个异常。这意味着如果您想将一个小型缓冲区传入一个大型数组，您需要明确地指定缓冲区中剩余的数据长度。上面的第一个例子不会如您第一眼所推出的结论那样，将缓冲区内剩余的数据元素复制到数组的底部。要将一个缓冲区释放到一个大数组中，要这样做：

<pre>
char [] bigArray = new char [1000];
// Get count of chars remaining in the buffer
int length = buffer.remaining( );
// Buffer is known to contain < 1,000 chars
buffer.get (bigArrray, 0, length);
// Do something useful with the
</pre>

另一方面，如果缓冲区存有比数组能容纳的数量更多的数据，您可以重复利用如下文所示的程序块进行读取：

<pre>
char [] smallArray = new char [10];
while (buffer.hasRemaining( )) {
    int length = Math.min (buffer.remaining( ), smallArray.length);
    buffer.get (smallArray, 0, length);
    processData (smallArray, length);
}
</pre>

如果缓冲区有足够的空间接受数组中的数据（buffer.remaining()>myArray.length)，数据将会被复制到从当前位置开始的缓冲区，并且缓冲区位置会被提前所增加数据元素的数量。如果缓冲区中没有足够的空间，那么不会有数据被传递，同时抛出一个BufferOverflowException异常。

### 创建缓冲区

<pre>
public static CharBuffer allocate(int capacity)
public static CharBuffer wrap(char[] array, int offset, int length)
public static CharBuffer wrap(char[] array)
public final boolean hasArray()
public final char[] array()
public final int arrayOffset()
</pre>

新的缓冲区是由分配或包装操作创建的。分配操作创建一个缓冲区对象并分配一个私有的空间来储存容量大小的数据元素。包装操作创建一个缓冲区对象但是不分配任何空间来储存数据元素。它使用您所提供的数组作为存储空间来储存缓冲区中的数据元素。

*从堆空间中分配了一个char型数组*

<pre>
CharBuffer charBuffer = CharBuffer.allocate (100);
</pre>

*使用自己的数组用做缓冲区*

<pre>
char [] myArray = new char [100];
CharBuffer charbuffer = CharBuffer.wrap (myArray);
</pre>

这段代码构造了一个新的缓冲区对象，但数据元素会存在于数组中。这意味着通过调用put()函数造成的对缓冲区的改动会直接影响这个数组，而且对这个数组的任何改动也会对这个缓冲区对象可见。

*指定position和limit*

<pre>
CharBuffer charbuffer = CharBuffer.wrap (myArray, 12, 42);
</pre>

创建了一个position值为12，limit值为54，容量为myArray.length的缓冲区。

通过allocate()或者wrap()函数创建的缓冲区通常都是间接的。间接的缓冲区使用备份数组，Boolean型函数hasArray()告诉您这个缓冲区是否有一个可存取的备份数组。如果这个函数的返回true，array()函数会返回这个缓冲区对象所使用的数组存储空间的引用。

如果hasArray()函数返回false，不要调用array()函数或者arrayOffset()函数。如果您这样做了您会得到一个UnsupportedOperationException异常。如果一个缓冲区是只读的，它的备份数组将会是超出上界的，即使一个数组对象被提供给wrap()函数。调用array()函数或者arrayOffset()会抛出一个ReadOnlyBufferException异常，来阻止您得到存取权来修改只读缓冲区的内容。如果您通过其它的方式获得了对备份数组的存取权限，对这个数组的修改也会直接影响到这个只读缓冲区。

arrayOffset()，返回缓冲区数据在数组中存储的开始位置的偏移量（从数组头0开始计算）。如果您使用了带有三个参数的版本的wrap()函数来创建一个缓冲区，对于这个缓冲区，arrayOffset()会一直返回0，像我们之前讨论的那样。然而，如果您切分了由一个数组提供存储的缓冲区，得到的缓冲区可能会有一个非0的数组偏移量。这个数组偏移量和缓冲区容量值会告诉您数组中哪些元素是被缓冲区使用的。

### 复制缓冲区

缓冲区不限于管理数组中的外部数据。它们也能管理其他缓冲区中的外部数据。当一个管理其他缓冲器所包含的数据元素的缓冲器被创建时，这个缓冲器被称为视图缓冲器。大多数的视图缓冲器都是ByteBuffer的视图。

视图存储器总是通过调用已存在的存储器实例中的函数来创建。使用已存在的存储器实例中的工厂方法意味着视图对象为原始存储器的内部实现细节私有。数据元素可以直接存取，无论它们是存储在数组中还是以一些其他的方式，而不需经过原始缓冲区对象的get()/put() API。如果原始缓冲区是直接缓冲区，该缓冲区的视图会具有同样的效率优势。映像缓冲区也是如此

<pre>
public abstract CharBuffer duplicate();
public abstract CharBuffer asReadOnlyBuffer();
public abstract CharBuffer slice();
</pre>

duplicate()函数创建了一个与原始缓冲区相似的新缓冲区。两个缓冲区共享数据元素，拥有同样的容量，但每个缓冲区拥有各自的位置，上界和标记属性。对一个缓冲区内的数据元素所做的改变会反映在另外一个缓冲区上。这一副本缓冲区具有与原始缓冲区同样的数据视图。如果原始的缓冲区为只读，或者为直接缓冲区，新的缓冲区将继承这些属性。

<pre>
CharBuffer buffer = CharBuffer.allocate (10);
buffer.position (3).limit (6).mark( ).position (5);
CharBuffer dupeBuffer = buffer.duplicate( );
buffer.clear( );
</pre>

<table>
    <tr>
        <td></td>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>buffer</td>
        <td>mark(x)、potision(0)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(10)、capacity(10)</td>
    </tr>
    <tr>
        <td>duplicate</td>
        <td></td>
        <td></td>
        <td></td>
        <td>mark(3)</td>
        <td></td>
        <td>potision(5)</td>
        <td>limit(6)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>capacity(10)</td>
    </tr>
</table>

您可以使用asReadOnlyBuffer()函数来生成一个只读的缓冲区视图。这与duplicate()相同，除了这个新的缓冲区不允许使用put()，并且其isReadOnly()函数将会返回true。对这一只读缓冲区的put()函数的调用尝试会导致抛出ReadOnlyBufferException异常。


#### 分割缓冲区

分割缓冲区与复制相似，但slice()创建一个从原始缓冲区的当前位置开始的新缓冲区，并且其容量是原始缓冲区的剩余元素数量（limit-position）。这个新缓冲区与原始缓冲区共享一段数据元素子序列。分割出来的缓冲区也会继承只读和直接属性

<pre>
CharBuffer buffer = CharBuffer.allocate (8);
buffer.position (3).limit (5);
CharBuffer sliceBuffer = buffer.slice( );
</pre>

<table>
    <tr>
        <td></td>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>buffer</td>
        <td>mark(x)、potision(0)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(10)、capacity(10)</td>
    </tr>
    <tr>
        <td>slice</td>
        <td></td>
        <td></td>
        <td></td>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>slice</td>
        <td></td>
        <td></td>
        <td></td>
        <td>mark(x)、potision(0)</td>
        <td></td>
        <td>limit(2)、capacity(2)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>

### 字节缓冲区
#### 字节顺序
大端字节顺序、小端字节顺序

ByteBuffer的字符顺序设定可以随时通过调用以ByteOrder.BIG_ENDIAN或ByteOrder.LITTL_ENDIAN为参数的order()函数来改变。

<pre>
public final ByteOrder order()
public final ByteBuffer order(ByteOrder bo)
</pre>

如果一个缓冲区被创建为一个ByteBuffer对象的视图（参见2.4.3节），那么order()返回的数值就是视图被创建时其创建源头的ByteBuffer的字节顺序设定。视图的字节顺序设定在创建后不能被改变，而且如果原始的字节缓冲区的字节顺序在之后被改变，它也不会受到影响

#### 直接缓冲区
字节缓冲区跟其他缓冲区类型最明显的不同在于，它们可以成为通道所执行的I/O的源头和/或目标。

出于这一原因，引入了直接缓冲区的概念。直接缓冲区被用于与通道和固有I/O例程交互。它们通过使用固有代码来告知操作系统直接释放或填充内存区域，对用于通道直接或原始存取的内存区域中的字节元素的存储尽了最大的努力。

直接字节缓冲区通常是I/O操作最好的选择。在设计方面，它们支持JVM可用的最高效I/O机制。非直接字节缓冲区可以被传递给通道，但是这样可能导致性能损耗。通常非直接缓冲不可能成为一个本地I/O操作的目标。如果您向一个通道中传递一个非直接ByteBuffer对象用于写入，通道可能会在每次调用中隐含地进行下面的操作：

    1.创建一个临时的直接ByteBuffer对象。
    2.将非直接缓冲区的内容复制到临时缓冲中。
    3.使用临时缓冲区执行低层次I/O操作。
    4.临时缓冲区对象离开作用域，并最终成为被回收的无用数据。

直接缓冲区是I/O的最佳选择，但可能比创建非直接缓冲区要花费更高的成本。直接缓冲区使用的内存是通过调用本地操作系统方面的代码分配的，绕过了标准JVM堆栈。建立和销毁直接缓冲区会明显比具有堆栈的缓冲区更加破费，这取决于主操作系统以及JVM实现。直接缓冲区的内存区域不受无用存储单元收集支配，因为它们位于标准JVM堆栈之外。

使用直接缓冲区或非直接缓冲区的性能权衡会因JVM，操作系统，以及代码设计而产生巨大差异。通过分配堆栈外的内存，您可以使您的应用程序依赖于JVM未涉及的其它力量。当加入其他的移动部分时，确定您正在达到想要的效果。我以一条旧的软件行业格言建议您：先使其工作，再加快其运行。不要一开始就过多担心优化问题；首先要注重正确性。JVM实现可能会执行缓冲区缓存或其他的优化，5这会在不需要您参与许多不必要工作的情况下为您提供所需的性能。

直接ByteBuffer是通过调用具有所需容量的ByteBuffer.allocateDirect()函数产生的，就像我们之前所涉及的allocate()函数一样。注意用一个wrap()函数所创建的被包装的缓冲区总是非直接的。

<pre>
public static ByteBuffer allocateDirect(int capacity)
public abstract boolean isDirect();
</pre>

#### 视图缓冲区
视图缓冲区通过已存在的缓冲区对象实例的工厂方法来创建。这种视图对象维护它自己的属性，容量，位置，上界和标记，但是和原来的缓冲区共享数据元素。ByteBuffer类允许创建视图来将byte型缓冲区字节数据映射为其它的原始数据类型。例如，asLongBuffer()函数创建一个将八个字节型数据当成一个long型数据来存取的视图缓冲区。

下面列出的每一个工厂方法都在原有的ByteBuffer对象上创建一个视图缓冲区。调用其中的任何一个方法都会创建对应的缓冲区类型，这个缓冲区是基础缓冲区的一个切分，由基础缓冲区的位置和上界决定。新的缓冲区的容量是字节缓冲区中存在的元素数量除以视图类型中组成一个数据类型的字节数（参见表2-1）。在切分中任一个超过上界的元素对于这个视图缓冲区都是不可见的。视图缓冲区的第一个元素从创建它的ByteBuffer对象的位置开始（positon()函数的返回值）。具有能被自然数整除的数据元素个数的视图缓冲区是一种较好的实现

<pre>
public abstract CharBuffer asCharBuffer();
public abstract DoubleBuffer asDoubleBuffer();
public abstract FloatBuffer asFloatBuffer();
public abstract IntBuffer asIntBuffer();
public abstract LongBuffer asLongBuffer();
public abstract ShortBuffer asShortBuffer();
</pre>

*创建一个ByteBuffer的字符视图*

<pre>
public class ByteCharView {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();

        byteBuffer.put(0, (byte) 0);
        byteBuffer.put(1, (byte) 'H');
        byteBuffer.put(2, (byte) 0);
        byteBuffer.put(3, (byte) 'i');
        byteBuffer.put(4, (byte) 0);
        byteBuffer.put(5, (byte) '!');
        byteBuffer.put(6, (byte) 0);

        println(byteBuffer);
        println(charBuffer);

    }

    private static void println(Buffer buffer) {
        System.out.println("pos=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity() + ": '" + buffer.toString() + "'");
    }
}
</pre>

输出：

    pos=0, limit=7, capacity=7: 'java.nio.HeapByteBuffer[pos=0 lim=7 cap=7]'
    pos=0, limit=3, capacity=3: 'Hi!'

<pre>
public class ByteCharView {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(ByteOrder.LITTLE_ENDIAN);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();

        byteBuffer.put(0, (byte) 'H');
        byteBuffer.put(1, (byte) 0);
        byteBuffer.put(2, (byte) 'i');
        byteBuffer.put(3, (byte) 0);
        byteBuffer.put(4, (byte) '!');
        byteBuffer.put(5, (byte) 0);
        byteBuffer.put(6, (byte) 0);

        println(byteBuffer);
        println(charBuffer);

    }

    private static void println(Buffer buffer) {
        System.out.println("pos=" + buffer.position() + ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity() + ": '" + buffer.toString() + "'");
    }
}
</pre>

#### 数据元素视图

<pre>
public abstract class ByteBuffer extends Buffer implements Comparable {
    public abstract char getChar();

    public abstract char getChar(int index);

    public abstract short getShort();

    public abstract short getShort(int index);

    public abstract int getInt();

    public abstract int getInt(int index);

    public abstract long getLong();

    public abstract long getLong(int index);

    public abstract float getFloat();

    public abstract float getFloat(int index);

    public abstract double getDouble();

    public abstract double getDouble(int index);

    public abstract ByteBuffer putChar(char value);

    public abstract ByteBuffer putChar(int index, char value);

    public abstract ByteBuffer putShort(short value);

    public abstract ByteBuffer putShort(int index, short value);

    public abstract ByteBuffer putInt(int value);

    public abstract ByteBuffer putInt(int index, int value);

    public abstract ByteBuffer putLong(long value);

    public abstract ByteBuffer putLong(int index, long value);

    public abstract ByteBuffer putFloat(float value);

    public abstract ByteBuffer putFloat(int index, float value);

    public abstract ByteBuffer putDouble(double value);

    public abstract ByteBuffer putDouble(int index, double value);
}
</pre>

这些函数从当前位置开始存取ByteBuffer的字节数据，就好像一个数据元素被存储在那里一样。根据这个缓冲区的当前的有效的字节顺序，这些字节数据会被排列或打乱成需要的原始数据类型。比如说，如果getInt()函数被调用，从当前的位置开始的四个字节会被包装成一个int类型的变量然后作为函数的返回值返回。

包含一些数据的ByteBuffer
<table>
    <tr>
        <td></td>
        <td>0</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td>07</td>
        <td>3B</td>
        <td>C5</td>
        <td>31</td>
        <td>5E</td>
        <td>94</td>
        <td>D6</td>
        <td>04</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td>mark(x)</td>
        <td>potision(1)</td>
        <td></td>
        <td></td>
        <td></td>
        <td>limit(5)</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>capacity(10)</td>
    </tr>
</table>

<code>int value = buffer.getInt( );</code>

会返回一个由缓冲区中位置1-4的byte数据值组成的int型变量的值。实际的返回值取决于缓冲区的当前的比特排序（byte-order）设置。更具体的写法是：
<code>int value = buffer.order (ByteOrder.BIG_ENDIAN).getInt( ); </code>这将会返回值0x3BC5315E，而<code>int value = buffer.order (ByteOrder.LITTLE_ENDIAN).getInt( ); </code>返回值0x5E31C53B。

#### 存取无符号数

#### 内存映射缓冲区
映射缓冲区是带有存储在文件，通过内存映射来存取数据元素的字节缓冲区。映射缓冲区通常是直接存取内存的，只能通过FileChannel类创建。映射缓冲区的用法和直接缓冲区类似，但是MappedByteBuffer对象可以处理独立于文件存取形式的的许多特定字符