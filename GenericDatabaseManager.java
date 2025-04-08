����   = �
      java/lang/Object <init> ()V  java/util/ArrayList
        makeConcatWithConstants &(Ljava/lang/String;)Ljava/lang/String;  GenericDatabaseManager  Qjdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false  root  password
      java/sql/DriverManager getConnection M(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;      ! java/sql/Connection prepareStatement 0(Ljava/lang/String;)Ljava/sql/PreparedStatement; # $ % & ' java/sql/PreparedStatement executeQuery ()Ljava/sql/ResultSet; ) * + , - java/sql/ResultSet getMetaData ()Ljava/sql/ResultSetMetaData; / 0 1 2 3 java/sql/ResultSetMetaData getColumnCount ()I ) 5 6 7 next ()Z 9 java/util/HashMap
 8  / < = > getColumnName (I)Ljava/lang/String; ) @ A > 	getString C D E F G java/util/Map put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; I J K L M java/util/List add (Ljava/lang/Object;)Z ) O P  close R java/lang/Throwable
 Q T U V addSuppressed (Ljava/lang/Throwable;)V # O  O Z java/sql/SQLException
 Y \ ]  printStackTrace _ Customer
  a b c 	fetchData $(Ljava/lang/String;)Ljava/util/List;	 e f g h i java/lang/System out Ljava/io/PrintStream; k 
Customers:
 m n o p q java/io/PrintStream println (Ljava/lang/String;)V
  s t u displayRecords (Ljava/util/List;)V w Staff y 
Staff: I { | 7 isEmpty ~ No data found. I � � � get (I)Ljava/lang/Object; C � � � keySet ()Ljava/util/Set; � � � � � java/util/Set iterator ()Ljava/util/Iterator; � � � � 7 java/util/Iterator hasNext � � 6 � ()Ljava/lang/Object; � java/lang/String  
 m � � q print � L
--------------------------------------------------------------------------- I � C � � � &(Ljava/lang/Object;)Ljava/lang/Object;
 m � p  DATABASE_URL Ljava/lang/String; ConstantValue USERNAME PASSWORD Code LineNumberTable StackMapTable 	Signature [(Ljava/lang/String;)Ljava/util/List<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>; main ([Ljava/lang/String;)V J(Ljava/util/List<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>;)V 
SourceFile GenericDatabaseManager.java BootstrapMethods �
 � � �  � $java/lang/invoke/StringConcatFactory �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite; � SELECT * FROM  � 	 InnerClasses � %java/lang/invoke/MethodHandles$Lookup � java/lang/invoke/MethodHandles Lookup !       � �  �      � �  �      � �  �          �        *� �    �        	 b c  �  _  
  � Y� 	L*� 
  M� N-,�  :� " :� ( :� . 6� 4 � B� 8Y� ::6		� #	� ; 	� ? � B W�	���+� H W���� *� N �  :� � N � :� S�� *� W �  :� � W � :� S�-� '-� X � :-� -� X � :� S�� N-� [+�  + � � Q � � � Q " � � Q � � � Q  � � Q � � � Q 	 Y  �   b           "  +  4  =  G  P  Z  t  z  �  �  �  �  �  �  �  !	 
   # �   � � =  � I �  # ) /  �  C� &� N Q�   � I �  # ) Q  Q� N Q�   � I �  # Q  Q� L Q�   � I �  Q  Q� B Y �    � 	 � �  �   U     %^� `L� dj� l+� rv� `M� dx� l,� r�    �       (  )  *  -  .   / $ 0 	 t u  �  (     �*� z � � d}� l�*�  � C� � L+� � M,� � � ,� � � �N� d-� �  � ���� d�� l*� � M,� � � M,� � � CN+� � :� � � (� � � �:� d-� � � �� �  � ���Բ d� �����    �   >    4 	 5  6  : " ; < < H = K > S A m B � C � D � E � F � G �   $ �  � �� !�  ��  C �� .�  �    �  �    � �     �  � �  � �   
  � � � 