Buffer: This works with a channel to process data

Java NIO�е�Buffer���ں�NIOͨ�����н�����
д��Buffer -> Channel
����Channel -> Buffer

ʹ��Buffer��д���ݵĲ��裺
1. Write data into the Buffer
2. Call buffer.flip()
3. Read data out of the Buffer
4. Call buffer.clear() or buffer.compact()

clear()���������������������
compact()����ֻ������Ѿ����������ݡ��κ�δ�������ݶ����Ƶ�����������ʼ������д������ݽ��ŵ�������δ�����ݵĺ��档