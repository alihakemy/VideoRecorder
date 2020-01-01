<p>this camera recorder library like whatsapp and messanger recorded while press on record buttonthis camera recorder library like whatsapp and messanger recorded while press on record button</p>
<p>&nbsp;</p>
<p>add the following code in Gradle Scripts&nbsp; allprojects {</p>
<p>repositories {</p>
<p>... maven { url 'https://jitpack.io' }}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>


<pre style="font-family: &quot;Fira Code&quot;; font-size: 11.3pt;">
   implementation <span style="color:#6a8759;">&#39;com.github.alihakemy:VideoRecorder:v2
&#39;</span></pre>

<p><span style="background-color: #ff6600;">&nbsp;&nbsp;note</span> (check write external storage permission and camera and mic )<br />&nbsp;&nbsp;&nbsp;&nbsp;</p>









<pre style="background-color:#2b2b2b;color:#a9b7c6;font-family:'Fira Code';font-size:11.3pt;">
Intent intent =<span style="color:#cc7832;">new </span>Intent(getApplicationContext()<span style="color:#cc7832;">,  </span>recordmain.<span style="color:#cc7832;">class</span>)<span style="color:#cc7832;">;
</span>startActivityForResult(intent<span style="color:#cc7832;">,</span><span style="color:#6897bb;">0</span>)<span style="color:#cc7832;">;



</span></pre>
<pre style="font-family: &quot;Fira Code&quot;; font-size: 11.3pt;">
<span style="color:#bbb529;">@Override
</span><span style="color:#cc7832;">protected void </span><span style="color:#ffc66d;">onActivityResult</span>(<span style="color:#cc7832;">int </span>requestCode<span style="color:#cc7832;">, int </span>resultCode<span style="color:#cc7832;">, </span><span style="color:#bbb529;">@Nullable </span>Intent data) {
    <span style="color:#cc7832;">if </span>(resultCode == <span style="color:#9876aa;font-style:italic;">RESULT_OK</span>) {
        <span style="color:#808080;">// A contact was picked.  Here we will just display it
</span><span style="color:#808080;">        // to the user.
</span><span style="color:#808080;">        </span>Log.<span style="font-style:italic;">e</span>(<span style="color:#6a8759;">&quot;data1&quot;</span><span style="color:#cc7832;">,</span>data.getStringExtra(<span style="color:#6a8759;">&quot;DATA&quot;</span>))<span style="color:#cc7832;">;
</span><span style="color:#cc7832;">        </span>String VideoPath =data.getStringExtra(<span style="color:#6a8759;">&quot;DATA&quot;</span>)<span style="color:#cc7832;">;
</span><span style="color:#cc7832;">       
</span><span style="color:#cc7832;">    </span>}
    <span style="color:#cc7832;">else
</span><span style="color:#cc7832;">    </span>{
        <span style="color:#cc7832;">super</span>.onActivityResult(requestCode<span style="color:#cc7832;">, </span>resultCode<span style="color:#cc7832;">, </span>data)<span style="color:#cc7832;">;
</span>
<span style="color:#cc7832;">    </span>}
}</pre>
<br />
