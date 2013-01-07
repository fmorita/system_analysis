# Description

This is a simple tools for replay an order algorithm for play with forex.


# Command line

## Synopsis
  $ system_analysis [--version] --url=&lt;url&gt; [--user=&lt;user&gt;] [--password=&lt;password&gt;] &lt;command&gt; [&lt;args&gt;]


## Options
* --url= &lt;database url&gt;
* --user=&lt;username&gt;
* --password=&lt;password&gt;

## Commandes

### init
データベースの初期化。DBが存在する場合は上書きしない

* --force DBの有無に関わらず初期化する。

### import
1分足を登録する

* --pair=USD/JPY、USDJPY.... なければファイル名から分析する
* --start 取引開始時 (デフォールト: 07:00)
* --end 取引終了時 (デフォールト: 翌日07:00)

### compute
各種計算を行う

* --historical　１分足から５分足、１時間足、日足、週足等を再計算する
* --index テクニカル指標の再計算
* --signal シグナルの再計算
* --judge 注文判定の再計算

### binary
Simulate trading with forex binary options

* --coeff: 倍率 (デフォールト: 2倍)
* --found: 原本 (デフォールト: 5万円)
* --price:　購入金額 (デフォールト: 1万円)
* --reception: 購入受付期間 (デフォールト: 10分)
* --process: 取引期間 (デフォールト: 10分)
* --startTime: 取引開始時間 (デフォールト: 08:00)
* --endTime: 取引終了時間 (デフォールト: 04:10)
