# リポジトリの概要
> 競技プログラミングで扱われるデータ構造をまとめています<br>
> すべて大規模データに掛けているので正しく動作するはず..<br>
> 基本的にジェネリクスで実装しているのでプリミティブ型と比べて + 400ms とかなり遅くなります（ほぼ記録用）

## 言語
> Java

## 動作環境
> openjdk version "16.0.1"

# データ構造の説明
># Monoid<br>
> 二項演算と単位元を実装するインターフェイスです<br>
> 詳しい内容は以下のURLを参考にしてください<br>[モノイドについての説明](https://zenn.dev/santamn/articles/81f4bf9a4cb139)
>
># SparseTable
> データ中の任意の範囲のMinやMaxなど冪等性を満たす演算の結果をO(1)で取得します(静的)<br>
> 詳しい内容は以下のURLを参考にしてください<br>[SparseTableについての説明](https://qiita.com/recuraki/items/0fcbc9e2abbc4fae5f62)
